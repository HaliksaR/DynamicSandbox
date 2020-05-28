package ru.shiftlaboratory.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import ru.shiftlaboratory.dynamicFeatures.providers.DDMProviderManager
import ru.shiftlaboratory.dynamicFeatures.providers.escorts.Escorts
import ru.shiftlaboratory.features.splash.navigation.NavigationActions
import ru.shiftlaboratory.libraries.navigation.navigate
import ru.shiftlaboratory.libraries.splitinstaller.SplitInstaller

class SplashFragment : Fragment() {
	private lateinit var viewModel: SplashViewModel
	private val navigationCommands by lazy { NavigationActions() }
	private lateinit var splitInstaller: SplitInstaller

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.splash_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
/*		splitInstaller = get {
			parametersOf(context, ConfirmationDialog(R.string.split_installer_btn_cancel, R.string.dynamic_module_escorts), splitInstallerView)
		}*/
		viewModel = getViewModel()

		DDMProviderManager.Koin.loadModule(Escorts.MODULE_PROVIDER) {
			onSuccess { navigate(navigationCommands.toDynamicFeature) }
			onError {}
		}

		DDMProviderManager.Koin.unloadModule(Escorts.MODULE_PROVIDER) {
			onSuccess {}
			onError {}
		}
	}
}
