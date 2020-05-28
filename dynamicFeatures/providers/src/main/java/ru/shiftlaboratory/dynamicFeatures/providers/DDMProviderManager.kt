package ru.shiftlaboratory.dynamicFeatures.providers

import android.util.Log
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.shiftlaboratory.dynamicFeatures.providers.base.ApiProviderClass
import ru.shiftlaboratory.dynamicFeatures.providers.base.ApiProviderFragment
import ru.shiftlaboratory.dynamicFeatures.providers.base.ApiProviderViewModel
import ru.shiftlaboratory.dynamicFeatures.providers.di.KoinFeatureModulesProvider

object DDMProviderManager {
	class StateProvider<T>(private val className: String) {
		private var onFeatureSuccess: (T) -> Unit = {}
		private var onFeatureError: () -> Unit = {}

		fun onSuccess(onFeatureSuccess: (T) -> Unit) {
			this.onFeatureSuccess = onFeatureSuccess
		}

		fun onError(onFeatureError: () -> Unit) {
			this.onFeatureError = onFeatureError
		}

		internal fun run() = try {
			val kClass = Class.forName(className).kotlin
			onFeatureSuccess((kClass.objectInstance ?: kClass.java.newInstance()) as T)
			Log.d("FeatureProviderManager", "onSuccess!")
		} catch (e: ClassNotFoundException) {
			onFeatureError()
			Log.d("FeatureProviderManager", "onError...")
		}
	}

	object Koin {
		class StateKoinProvider<T : KoinFeatureModulesProvider>(private val className: String) {
			private var onFeatureSuccess: () -> Unit = {}
			private var onFeatureError: () -> Unit = {}

			fun onSuccess(onFeatureSuccess: () -> Unit) {
				this.onFeatureSuccess = onFeatureSuccess
			}

			fun onError(onFeatureError: () -> Unit) {
				this.onFeatureError = onFeatureError
			}

			internal fun run(load: Boolean) = try {
				val kClass = Class.forName(className).kotlin
				val provider = ((kClass.objectInstance ?: kClass.java.newInstance()) as T)
				if (load) loadKoinModules(provider.modules) else unloadKoinModules(provider.modules)
				onFeatureSuccess()
				Log.d("FeatureProviderManager", "onSuccess!")
			} catch (e: ClassNotFoundException) {
				onFeatureError()
				Log.d("FeatureProviderManager", "onError...")
			}
		}

		fun loadModule(
			className: String,
			func: StateKoinProvider<KoinFeatureModulesProvider>.() -> Unit
		) = StateKoinProvider<KoinFeatureModulesProvider>(className)
			.apply(func)
			.run(true)

		fun unloadModule(
			className: String,
			func: StateKoinProvider<KoinFeatureModulesProvider>.() -> Unit
		) = StateKoinProvider<KoinFeatureModulesProvider>(className)
			.apply(func)
			.run(false)

		fun getProvider(
			className: String,
			func: StateProvider<KoinFeatureModulesProvider>.() -> Unit
		) = StateProvider<KoinFeatureModulesProvider>(className)
			.apply(func)
			.run()
	}

	fun <V : ApiProviderFragment> getView(
		className: String,
		func: StateProvider<V>.() -> Unit
	) = StateProvider<V>(className)
		.apply(func)
		.run()

	fun <VM : ApiProviderViewModel> getViewModel(
		className: String,
		func: StateProvider<VM>.() -> Unit
	) = StateProvider<VM>(className)
		.apply(func)
		.run()

	fun <C : ApiProviderClass> getClass(
		className: String,
		func: StateProvider<C>.() -> Unit
	) = StateProvider<C>(className)
		.apply(func)
		.run()
}