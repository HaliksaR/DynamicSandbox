package ru.shiftlaboratory.dynamicFeatures.providers.escorts

import ru.shiftlaboratory.dynamicFeatures.providers.FeatureBasicDirs.FEATURE_PATH
import ru.shiftlaboratory.dynamicFeatures.providers.FeatureBasicDirs.KOIN_PROVIDER_DIR
import ru.shiftlaboratory.dynamicFeatures.providers.R

object Escorts {
	private const val PACKAGE_NAME = "$FEATURE_PATH.escorts"
	val NAME = R.string.dynamic_module_escorts

	const val MODULE_SCREEN_PROVIDER = "$PACKAGE_NAME.$KOIN_PROVIDER_DIR.EscortsScreenModuleProviderImpl"
	const val MODULE_HOME_COMPONENT_PROVIDER = "$PACKAGE_NAME.$KOIN_PROVIDER_DIR.EscortsHomeCompModuleProviderImpl"
	const val HOME_COMPONENT = "$PACKAGE_NAME.components.home.EscortHomeComponent"
}