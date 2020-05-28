package ru.shiftlaboratory.features.splash

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.splash_fragment.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import ru.shiftlaboratory.dynamicFeatures.providers.DDMProviderManager
import ru.shiftlaboratory.dynamicFeatures.providers.escorts.Escorts
import ru.shiftlaboratory.features.splash.navigation.NavigationActions
import ru.shiftlaboratory.libraries.splitinstaller.ConfirmationDialog
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
		splitInstaller = get {
			parametersOf(context, ConfirmationDialog(R.string.split_installer_btn_cancel, R.string.dynamic_module_escorts), splitInstallerView)
		}
		viewModel = getViewModel()

		splitInstallerView.setOnClickListenerStatus {
			Toast.makeText(context, "Hello2", Toast.LENGTH_LONG).show()
		}

		splitInstallerView.setOnClickListenerOnLayout {
			AlertDialog.Builder(requireContext()).apply {
				setTitle("Скачать что-то")
				setMessage("будет круто")
				setPositiveButton(R.string.split_installer_btn_download) { _: DialogInterface?, _: Int ->
					splitInstallerView.displayDownloadingStateTest()
				}
				setNegativeButton(R.string.split_installer_btn_cancel) { _: DialogInterface?, _: Int ->
					null
				}
			}.show()
		}


		DDMProviderManager.Koin.loadModule(Escorts.MODULE_SCREEN_PROVIDER) {
			onSuccess {}
			onError {}
		}

		DDMProviderManager.Koin.unloadModule(Escorts.MODULE_SCREEN_PROVIDER) {
			onSuccess {}
			onError {}
		}

		DDMProviderManager.Koin.loadModule(Escorts.MODULE_HOME_COMPONENT_PROVIDER) {
			onSuccess {}
			onError {}
		}

		DDMProviderManager.Koin.unloadModule(Escorts.MODULE_HOME_COMPONENT_PROVIDER) {
			onSuccess {}
			onError {}
		}
	}
}
