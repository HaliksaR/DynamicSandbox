package ru.shiftlaboratory.dynamicFeatures.escorts.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shiftlaboratory.dynamicFeatures.escorts.EscortsViewModel

internal fun injectDynamicFeature() = loader

private val loader by lazy {
	loadKoinModules(EscortsModule)
}

val EscortsModule = module {
	viewModel { EscortsViewModel() }
}
