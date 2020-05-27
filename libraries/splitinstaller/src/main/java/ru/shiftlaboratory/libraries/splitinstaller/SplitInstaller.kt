package ru.shiftlaboratory.libraries.splitinstaller

import android.app.Activity
import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import ru.shiftlaboratory.libraries.splitinstaller.view.SplitInstallerViewBase

class SplitInstaller(
	private val activity: Activity,
	private val splitInstallerViewBase: SplitInstallerViewBase
) {

	private val manager: SplitInstallManager by lazy {
		SplitInstallManagerFactory.create(activity)
	}

	private var featuresName = mutableListOf<String>()

	private var onFeatureReady: () -> Unit = {}
	private var onFeatureError: () -> Unit = {}

	private val listener = SplitInstallStateUpdatedListener { state ->
		val names = state.moduleNames().joinToString(", ")
		when (state.status()) {
			SplitInstallSessionStatus.DOWNLOADING                -> {
				splitInstallerViewBase.displayDownloadingState(state, activity.getString(R.string.state_dynamic_module_downloading, names))
				Log.d("state", "DOWNLOADING")
			}

			SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
				manager.startConfirmationDialogForResult(state, activity, CONFIRMATION_REQUEST_CODE)
				Log.d("state", "REQUIRES_USER_CONFIRMATION")
			}

			SplitInstallSessionStatus.INSTALLED                  -> {
				onFeatureReady()
				Log.d("state", "INSTALLED")
			}

			SplitInstallSessionStatus.INSTALLING                 -> {
				splitInstallerViewBase.displayInstallingState(activity.getString(R.string.state_dynamic_module_installing, names))
				Log.d("state", "INSTALLING")
			}

			SplitInstallSessionStatus.FAILED                     -> {
				splitInstallerViewBase.displayFailedState(
					activity.getString(R.string.state_dynamic_module_error, state.errorCode(), state.moduleNames())
				)
				Log.d("state", "FAILED")
			}

			else                                                 -> Log.d("state", "else")

		}
	}

	private fun registerListener() {
		manager.registerListener(listener)
	}

	private fun unregisterListener() {
		manager.unregisterListener(listener)
	}

	fun getDynamicFeature(names: List<Int>, func: SplitInstaller.() -> Unit): SplitInstaller {
		registerListener()
		names.map { featuresName.add(activity.getString(it)) }
		this.func()
		checkFeature()
		unregisterListener()
		return this
	}

	fun onFeatureReady(onFeatureReady: () -> Unit) {
		this.onFeatureReady = onFeatureReady
	}

	fun onFeatureError(onFeatureError: () -> Unit) {
		this.onFeatureError = onFeatureError
	}

	private fun checkFeature() {
		featuresName.forEach {
			if (it.isEmpty()) throw IllegalArgumentException("Feature name not provided")
		}
		Log.d("installedModules", manager.installedModules.toString())
		if (manager.installedModules.containsAll(featuresName)) onFeatureReady()
		else startInstall()
	}

	private fun startInstall() {
		val requestBuilder = SplitInstallRequest.newBuilder()
		featuresName.forEach {
			if (!manager.installedModules.contains(it)) {
				requestBuilder.addModule(it)
			}
		}
		manager.startInstall(requestBuilder.build())
			.addOnFailureListener {
				onFeatureError()
			}.addOnSuccessListener {
				onFeatureReady()
			}
	}

	companion object {
		private const val CONFIRMATION_REQUEST_CODE = 1
	}

}