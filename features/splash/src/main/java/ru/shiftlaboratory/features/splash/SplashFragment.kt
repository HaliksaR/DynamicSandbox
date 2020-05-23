package ru.shiftlaboratory.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.splash_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import ru.shiftlaboratory.features.splash.navigation.NavigationActions
import ru.shiftlaboratory.libraries.navigation.navigate

class SplashFragment : Fragment() {
	private lateinit var viewModel: SplashViewModel
	private val navigationCommands by lazy { NavigationActions() }

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		viewModel = getViewModel()
		return inflater.inflate(R.layout.splash_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		btn.setOnClickListener {
			navigate(navigationCommands.toDynamicFeature)
/*			navigateToDynamic(
				name = getString(R.string.dynamic_module_escorts),
				navCommand = navigationCommands.toDynamicFeature
			)*/
		}
	}
}
