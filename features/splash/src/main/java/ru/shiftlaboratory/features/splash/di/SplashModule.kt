package ru.shiftlaboratory.features.splash.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.shiftlaboratory.features.splash.SplashViewModel

val SplashModule = module {
	viewModel { SplashViewModel() }
}