package ru.shiftlaboratory.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.shiftlaboratory.app.presentation.AppViewModel

internal val AppModule = module {
	viewModel { AppViewModel() }
}