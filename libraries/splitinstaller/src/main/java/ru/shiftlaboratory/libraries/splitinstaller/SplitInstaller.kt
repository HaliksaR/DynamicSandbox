package ru.shiftlaboratory.libraries.splitinstaller

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import ru.shiftlaboratory.libraries.splitinstaller.view.SplitInstallerViewBase

data class ConfirmationDialog(@StringRes val title: Int, @StringRes val message: Int)

class SplitInstaller(
	private val context: Context,
	private val dialog: ConfirmationDialog,
	private val splitInstallerViewBase: SplitInstallerViewBase
) {

	private val manager: FakeSplitInstallManager by lazy {
		FakeSplitInstallManagerFactory.create(context).apply {
			setShouldNetworkError(true)
		}
	}

	private var featureName = ""

	private var onFeatureReady: () -> Unit = {}
	private var onFeatureError: () -> Unit = {}

	private fun showConfirmationDialog() {
		with(dialog) {
			AlertDialog.Builder(context).apply {
				setTitle(title)
				setMessage(message)
				setPositiveButton("Скачать") { _: DialogInterface?, _: Int -> startInstall() }
				setNegativeButton("Закрыть") { _: DialogInterface?, _: Int -> null }
			}.show()
		}
	}

	fun getDynamicFeature(name: Int, func: SplitInstaller.() -> Unit): SplitInstaller {
		featureName = context.getString(name)
		this.func()
		checkFeature()
		return this
	}

	fun onFeatureReady(onFeatureReady: () -> Unit) {
		this.onFeatureReady = onFeatureReady
	}

	fun onFeatureError(onFeatureError: () -> Unit) {
		this.onFeatureError = onFeatureError
	}

	private fun checkFeature() {
		if (featureName.isEmpty()) throw IllegalArgumentException("Feature name not provided")
		if (manager.installedModules.contains(featureName)) onFeatureReady()
		else {
			with(splitInstallerViewBase) {
				setOnClickListenerStatus(::showConfirmationDialog)
				setOnClickListenerOnRefresh(::startInstall)
				setOnClickListenerOnCancel(this::displayStartState)
			}
		}
	}

	private var mySessionId = 0

	private val listener = SplitInstallStateUpdatedListener { state ->
		if (state.sessionId() == mySessionId) {
			with(splitInstallerViewBase) {
				Toast.makeText(context, "registerListener", Toast.LENGTH_LONG).show()
				setOnClickListenerStatus {
					manager.cancelInstall(state.sessionId())
				}
				when (state.status()) {
					SplitInstallSessionStatus.DOWNLOADING -> {
						displayDownloadingState(state, context.getString(R.string.state_dynamic_module_downloading, featureName))
					}

					SplitInstallSessionStatus.INSTALLING  -> {
						displayInstallingState(context.getString(R.string.state_dynamic_module_installing, featureName))
					}

					SplitInstallSessionStatus.INSTALLED   -> {
						onFeatureReady()
					}

					SplitInstallSessionStatus.FAILED      -> {
						displayFailedState(context.getString(R.string.state_dynamic_module_error, state.errorCode(), state.moduleNames()))
						onFeatureError()
					}
				}
			}
		}
	}

	private fun startInstall() {
		val request = SplitInstallRequest
			.newBuilder()
			.addModule(featureName)
			.build()

		manager.registerListener(listener)
		manager.startInstall(request)
			.addOnSuccessListener { mySessionId = it }
	}
}