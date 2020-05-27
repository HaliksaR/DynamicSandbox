package ru.shiftlaboratory.dynamicFeatures.escorts.screens.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shiftlaboratory.dynamicFeatures.escorts.screens.EscortsViewModel

internal fun injectDynamicFeature() = loader

private val loader by lazy {
	loadKoinModules(EscortsModule)
}

val EscortsModule = module {
	viewModel { EscortsViewModel() }
}
