package ru.shiftlaboratory.dynamicFeatures.escorts.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.escorts_fragment.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.shiftlaboratory.dynamicFeatures.escorts.R
import ru.shiftlaboratory.dynamicFeatures.escorts.screens.navigation.NavigationActions
import ru.shiftlaboratory.libraries.navigation.navigate
import ru.shiftlaboratory.libraries.network.Network
import ru.shiftlaboratory.libraries.storage.Storage

class EscortsFragment : Fragment() {

	private val escortsViewModel: EscortsViewModel by viewModel()
	private val navigationActions: NavigationActions by lazy { NavigationActions() }

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.escorts_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		escortsViewModel.apply { 0 }
		val storage = get<Storage>()
		val network = get<Network>()
		storage.sendToStorage("ggg")
		network.sendToInternet("GGG")
		btn.setOnClickListener {
			navigate(navigationActions.toSplash)
		}
	}
}
