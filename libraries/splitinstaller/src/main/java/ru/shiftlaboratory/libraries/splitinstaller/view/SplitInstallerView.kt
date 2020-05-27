package ru.shiftlaboratory.libraries.splitinstaller.view

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.play.core.splitinstall.SplitInstallSessionState

class SplitInstallerView(
	private val message: TextView,
	private val progress: ProgressBar
) {

	private val GAUGE_ANIMATION_INTERPOLATOR = DecelerateInterpolator(2F)
	private val MAX_LEVEL = 100
	private val GAUGE_ANIMATION_DURATION: Long = 5000
	private val objectAnimator: ObjectAnimator = ObjectAnimator.ofInt(progress, "progress", 0, MAX_LEVEL)

	fun displayDownloadingState(state: SplitInstallSessionState, message: String) {
		this.message.visibility = View.VISIBLE
		progress.visibility = View.VISIBLE
		objectAnimator.cancel()
		progress.max = state.totalBytesToDownload().toInt()
		progress.progress = state.bytesDownloaded().toInt()
		this.message.text = message
	}

	fun displayInstallingState(message: String) {
		this.message.visibility = View.VISIBLE
		progress.visibility = View.VISIBLE
		objectAnimator.interpolator = GAUGE_ANIMATION_INTERPOLATOR
		objectAnimator.duration = GAUGE_ANIMATION_DURATION
		objectAnimator.start()
		this.message.text = message
	}

	fun displayFailedState(message: String) {
		this.message.visibility = View.VISIBLE
		progress.visibility = View.GONE
		this.message.text = message
	}
}