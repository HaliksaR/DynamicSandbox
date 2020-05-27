package ru.shiftlaboratory.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.splash_fragment.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
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
		splitInstaller.getDynamicFeature(R.string.dynamic_module_escorts) {
			onFeatureReady {
				Toast.makeText(context, "done", Toast.LENGTH_LONG).show()
			}
		}
	}
}
