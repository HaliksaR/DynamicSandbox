package ru.shiftlaboratory.libraries.splitinstaller.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.google.android.play.core.splitinstall.SplitInstallSessionState

abstract class SplitInstallerViewBase(
	context: Context,
	attrs: AttributeSet?,
	layout: Int
) : FrameLayout(context, attrs) {
	init {
		View.inflate(context, layout, this)
	}

	abstract fun displayStartState()
	abstract fun displayDownloadingState(state: SplitInstallSessionState, message: String)
	abstract fun displayInstallingState(message: String)
	abstract fun displayFailedState(message: String)

	abstract fun setOnClickListenerOnRefresh(listener: () -> Unit)
	abstract fun setOnClickListenerOnCancel(listener: () -> Unit)
	abstract fun setOnClickListenerStatus(listener: (() -> Unit)?)
	abstract fun displayCompleteState()
}
