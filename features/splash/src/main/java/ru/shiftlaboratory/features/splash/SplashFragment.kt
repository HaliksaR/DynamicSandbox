package ru.shiftlaboratory.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.splash_fragment.*
import ru.shiftlaboratory.features.splash.navigation.NavigationCommands
import ru.shiftlaboratory.libraries.navigation.navigate

class SplashFragment : Fragment() {

	companion object {
		fun newInstance() = SplashFragment()
	}

	private lateinit var viewModel: SplashViewModel
	private val navigationCommands by lazy { NavigationCommands() }

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.splash_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		btn.setOnClickListener {
			navigate(navigationCommands.toDynamicFeature)
		}
	}
}
