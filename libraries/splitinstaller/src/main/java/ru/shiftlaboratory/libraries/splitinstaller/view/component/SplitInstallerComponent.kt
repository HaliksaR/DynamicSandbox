package ru.shiftlaboratory.libraries.splitinstaller.view.component

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
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
	private val objectAnimator: ObjectAnimator by lazy {
		ObjectAnimator.ofInt(loader, "progress", 0, MAX_LEVEL).apply {
			interpolator = GAUGE_ANIMATION_INTERPOLATOR
			duration = GAUGE_ANIMATION_DURATION
		}
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

	override fun displayStartState() {
		animation(false)
		title.text = startTitle
		description.text = startDescription
		btn_container.visibility = View.GONE
		loader.visibility = View.GONE
		statusImage(R.drawable.ic_download_split_manager)
	}

	override fun displayDownloadingState(state: SplitInstallSessionState, message: String) {
		loader.apply {
			animation(false)
			download(state)
		}
		title.text = message
		description.text = resources.getString(R.string.split_installer_btn_description_download)
		statusImage(R.drawable.ic_cancel_split_manager)
	}

	override fun displayInstallingState(message: String) {
		animation(true)
		title.text = message
		description.text = resources.getString(R.string.split_installer_btn_description_download)
		statusImage(R.drawable.ic_cancel_split_manager)
	}

	override fun displayCompleteState() {
		split_installer_view.visibility = View.GONE
		dynamic_feature_container.visibility = View.VISIBLE
	}

	fun setDynamicFeature(view: View) =
		dynamic_feature_container.addView(view)

	override fun displayFailedState(message: String) {
		animation(false)
		title.text = message
		description.visibility = View.GONE
		loader.visibility = View.GONE
		btn_container.visibility = View.VISIBLE
		statusImage(R.drawable.ic_error_split_manager)
	}

	private fun animation(state: Boolean) = with(objectAnimator) {
		if (state) start() else cancel()
	}

	private fun ProgressBar.download(state: SplitInstallSessionState) {
		visibility = View.VISIBLE
		max = state.totalBytesToDownload().toInt()
		progress = state.bytesDownloaded().toInt()
	}

	private fun statusImage(@DrawableRes drawableRes: Int) =
		btn_status.setImageDrawable(ContextCompat.getDrawable(context, drawableRes))

	override fun setOnClickListenerStatus(listener: () -> Unit) =
		btn_status.setOnClickListener { listener() }

	override fun setOnClickListenerOnRefresh(listener: () -> Unit) =
		btn_refresh.setOnClickListener { listener() }

	override fun setOnClickListenerOnCancel(listener: () -> Unit) =
		btn_cancel.setOnClickListener { listener() }
}