package ru.shiftlaboratory.libraries.splitinstaller.view.component

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import kotlinx.android.synthetic.main.split_installer_component.view.*
import ru.shiftlaboratory.libraries.splitinstaller.R
import ru.shiftlaboratory.libraries.splitinstaller.view.SplitInstallerViewBase


class SplitInstallerComponent(
	context: Context,
	attrs: AttributeSet?
) : SplitInstallerViewBase(context, attrs, R.layout.split_installer_component) {

	private var startTitle: String
	private var startDescription: String

	private val GAUGE_ANIMATION_INTERPOLATOR = DecelerateInterpolator(2F)
	private val MAX_LEVEL = 100
	private val GAUGE_ANIMATION_DURATION: Long = 5000
	private val objectAnimator: ObjectAnimator = ObjectAnimator.ofInt(loader, "progress", 0, MAX_LEVEL).apply {
		interpolator = GAUGE_ANIMATION_INTERPOLATOR
		duration = GAUGE_ANIMATION_DURATION
	}

	init {
		with(context.obtainStyledAttributes(attrs, R.styleable.SplitInstallerComponent)) {
			startTitle = this.getString(R.styleable.SplitInstallerComponent_titleText)
				?: throw RuntimeException("SplitInstallerComponent attr titleTextPattern empty")
			startDescription = this.getString(R.styleable.SplitInstallerComponent_descriptionText)
				?: throw RuntimeException("SplitInstallerComponent attr descriptionTextPattern empty")
		}

		displayStartState()
	}

	private fun animation(state: Boolean) {
		when (state) {
			true  -> objectAnimator.start()
			false -> objectAnimator.cancel()
		}
	}

	private fun ProgressBar.download(state: SplitInstallSessionState) {
		visibility = View.VISIBLE
		max = state.totalBytesToDownload().toInt()
		progress = state.bytesDownloaded().toInt()
	}

	override fun displayDownloadingState(state: SplitInstallSessionState, message: String) {
		loader.apply {
			animation(false)
			download(state)
		}
		title.text = message
		description.text = resources.getString(R.string.split_installer_btn_description_download)
	}

	override fun displayInstallingState(message: String) {
		animation(true)
		title.text = message
		description.text = resources.getString(R.string.split_installer_btn_description_download)
	}

	override fun displayFailedState(message: String) {
		animation(false)
		title.text = message
		description.visibility = View.GONE
		loader.visibility = View.GONE
		btn_container.visibility = View.VISIBLE
	}

	override fun displayStartState() {
		animation(false)
		title.text = startTitle
		description.text = startDescription
		btn_container.visibility = View.GONE
		loader.visibility = View.GONE
	}

	override fun setOnClickListenerStatus(listener: () -> Unit) =
		btn_status.setOnClickListener { listener() }

	override fun setOnClickListenerOnRefresh(listener: () -> Unit) =
		btn_refresh.setOnClickListener { listener() }

	override fun setOnClickListenerOnCancel(listener: () -> Unit) =
		btn_cancel.setOnClickListener { listener() }
}