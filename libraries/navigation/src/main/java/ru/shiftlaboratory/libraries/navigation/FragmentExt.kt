package ru.shiftlaboratory.libraries.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(navCommand: NavCommand, hostId: Int? = null) {
	if (hostId == null) {
		findNavController()
	} else {
		Navigation.findNavController(requireActivity(), hostId)
	}.navigate(navCommand.action, navCommand.args, navCommand.navOptions)
}
/*



fun Fragment.navigateToDynamic(
	progressTextView: TextView, // можно подавать специальное кастом вью для
	// загрузки в котором будет спинер или прогресс и текст состояний
	// ну и будет тут абстракция, а у нее классы по типу фулл окна или встраевыемые
	name: String,
	navCommand: NavCommand,
	hostId: Int? = null
) {
	if (hostId == null) {
		findNavController()
	} else {
		Navigation.findNavController(requireActivity(), hostId)
	}.navigate(navCommand.action, navCommand.args, navCommand.navOptions)
}

private val listener = SplitInstallStateUpdatedListener { state ->
	val multiInstall = state.moduleNames().size > 1
	val langsInstall = state.languages().isNotEmpty()

	val names = if (langsInstall) state.languages().first()
	else state.moduleNames().joinToString(", ")

	when (state.status()) {
		SplitInstallSessionStatus.DOWNLOADING                -> {
			//  In order to see this, the application has to be uploaded to the Play Store.
			displayLoadingState(state, getString(R.string.downloading, names))
		}
		SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
			*/
/*
			  This may occur when attempting to download a sufficiently large module.
			  In order to see this, the application has to be uploaded to the Play Store.
			  Then features can be requested until the confirmation path is triggered.
			 *//*

			manager.startConfirmationDialogForResult(state, this, CONFIRMATION_REQUEST_CODE)
		}
		SplitInstallSessionStatus.INSTALLED -> {
			if (langsInstall){
				onSuccessfulLanguageLoad(names)
			} else {
				onSuccessfulLoad(names, launch = !multiInstall)
			}
		}

		SplitInstallSessionStatus.INSTALLING -> displayLoadingState(
			state,
			getString(R.string.installing, names)
		)
		SplitInstallSessionStatus.FAILED -> {
			toastAndLog(getString(R.string.error_for_module, state.errorCode(),
				state.moduleNames()))
		}
	}
}

private fun loadAndLaunchModule(name: String) {

}*/
