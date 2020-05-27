package ru.shiftlaboratory.libraries.splitinstaller.di

import android.app.Activity
import org.koin.dsl.module
import ru.shiftlaboratory.libraries.splitinstaller.SplitInstaller
import ru.shiftlaboratory.libraries.splitinstaller.view.SplitInstallerViewBase

val SplitInstallerModule = module {
	single { (activity: Activity, viewBase: SplitInstallerViewBase) ->
		SplitInstaller(activity = activity, splitInstallerViewBase = viewBase)
	}
}