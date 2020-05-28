package ru.shiftlaboratory.dynamicFeatures.escorts.koinProvider

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.shiftlaboratory.dynamicFeatures.escorts.screens.EscortsViewModel
import ru.shiftlaboratory.dynamicFeatures.providers.di.KoinFeatureModulesProvider

object EscortsScreenModuleProviderImpl : KoinFeatureModulesProvider {
	override val modules = listOf(EscortsScreenModule)
}

val EscortsScreenModule = module {
	viewModel { EscortsViewModel() }
}
