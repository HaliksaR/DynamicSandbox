package ru.shiftlaboratory.dynamicFeatures.escorts.koinProvider

import org.koin.dsl.module
import ru.shiftlaboratory.dynamicFeatures.escorts.components.home.EscortHomeComponent
import ru.shiftlaboratory.dynamicFeatures.escorts.components.home.EscortHomeComponentViewModel
import ru.shiftlaboratory.dynamicFeatures.providers.di.KoinFeatureModulesProvider
import ru.shiftlaboratory.dynamicFeatures.providers.escorts.EscortsHomeComponentProviderApi

object EscortsHomeCompModuleProviderImpl : KoinFeatureModulesProvider {
	override val modules = listOf(HomeComponent, HomeComponentViewModel)
}

internal val HomeComponent = module {
	factory<EscortsHomeComponentProviderApi> { EscortHomeComponent() }
}

internal val HomeComponentViewModel = module {
	factory { EscortHomeComponentViewModel() }
}
