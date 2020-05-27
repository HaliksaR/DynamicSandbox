package ru.shiftlaboratory.libraries.splitinstaller.di

import android.content.Context
import org.koin.dsl.module
import ru.shiftlaboratory.libraries.splitinstaller.ConfirmationDialog
import ru.shiftlaboratory.libraries.splitinstaller.SplitInstaller
import ru.shiftlaboratory.libraries.splitinstaller.view.SplitInstallerViewBase

val SplitInstallerModule = module {
	single { (context: Context, dialog: ConfirmationDialog, viewBase: SplitInstallerViewBase) ->
		SplitInstaller(context = context, dialog = dialog, splitInstallerViewBase = viewBase)
	}
}