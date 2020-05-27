package ru.shiftlaboratory.libraries.splitinstaller.di

import android.app.Activity
import org.koin.dsl.module
import ru.shiftlaboratory.libraries.splitinstaller.SplitInstaller
import ru.shiftlaboratory.libraries.splitinstaller.view.SplitInstallerView

val SplitInstallerModule = module {
	single { (activity: Activity, view: SplitInstallerView) ->
		SplitInstaller(activity = activity, splitInstallerView = view)
	}
}