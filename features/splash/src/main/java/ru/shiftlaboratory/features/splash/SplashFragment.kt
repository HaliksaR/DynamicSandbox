package ru.shiftlaboratory.features.splash

import android.os.Bundle
import android.util.Log
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
import ru.shiftlaboratory.libraries.navigation.navigate
import ru.shiftlaboratory.libraries.splitinstaller.SplitInstaller
import ru.shiftlaboratory.libraries.splitinstaller.view.SplitInstallerView

class SplashFragment : Fragment() {
	private lateinit var viewModel: SplashViewModel
	private val navigationCommands by lazy { NavigationActions() }
	private lateinit var splitInstaller: SplitInstaller
	private lateinit var splitView: SplitInstallerView

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.splash_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		splitView = SplitInstallerView(message = textView, progress = progressBar)
		splitInstaller = get { parametersOf(requireActivity(), splitView) }
		viewModel = getViewModel()
		button.visibility = View.GONE
		button2.visibility = View.GONE
		button.setOnClickListener {
			navigateToDynamicFeature()
		}
		button2.setOnClickListener {
			Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_LONG).show()
		}

		btn.setOnClickListener {
			navigateToDynamicFeature()
		}
	}

	private fun navigateToDynamicFeature() {
		splitInstaller.getDynamicFeature(listOf(R.string.dynamic_module_escorts)) {
			onFeatureReady {
				navigate(navigationCommands.toDynamicFeature)
			}
			onFeatureError {
				button.visibility = View.VISIBLE
				button2.visibility = View.VISIBLE
				Log.d("state", "onFeatureError")
			}
		}
	}
}
