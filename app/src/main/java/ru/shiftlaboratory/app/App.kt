package ru.shiftlaboratory.app

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			printLogger()
			androidContext(this@App)
			modules()
		}
	}

	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		SplitCompat.install(this)
	}
}