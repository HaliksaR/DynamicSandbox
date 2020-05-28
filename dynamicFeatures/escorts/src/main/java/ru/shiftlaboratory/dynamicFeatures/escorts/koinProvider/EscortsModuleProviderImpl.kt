package ru.shiftlaboratory.dynamicFeatures.escorts.koinProvider

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.shiftlaboratory.dynamicFeatures.escorts.components.home.EscortHomeComponent
import ru.shiftlaboratory.dynamicFeatures.escorts.components.home.EscortHomeComponentViewModel
import ru.shiftlaboratory.dynamicFeatures.escorts.screens.EscortsViewModel
import ru.shiftlaboratory.dynamicFeatures.providers.di.KoinFeatureModulesProvider
import ru.shiftlaboratory.dynamicFeatures.providers.escorts.EscortsHomeComponentProviderApi

val EscortsModule = module {
	viewModel { EscortsViewModel() }
}

object EscortsModuleProviderImpl : KoinFeatureModulesProvider {
	override val modules = listOf(EscortsModule)
}

internal val HomeComponent = module {
	factory<EscortsHomeComponentProviderApi> { EscortHomeComponent() }
}

internal val HomeComponentViewModel = module {
	factory { EscortHomeComponentViewModel() }
}
