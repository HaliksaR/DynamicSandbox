package ru.shiftlaboratory.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.shiftlaboratory.app.di.AppModule
import ru.shiftlaboratory.features.splash.di.SplashModule
import ru.shiftlaboratory.libraries.network.di.NetworkModule
import ru.shiftlaboratory.libraries.splitinstaller.di.SplitInstallerModule
import ru.shiftlaboratory.libraries.storage.di.StorageModule

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			printLogger()
			androidContext(this@App)
			modules(AppModule + SplitInstallerModule + SplashModule + NetworkModule + StorageModule)
		}
	}
/*
	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		SplitCompat.install(this)
	}*/
}