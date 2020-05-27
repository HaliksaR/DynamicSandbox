package ru.shiftlaboratory.dynamicFeatures.escorts.components.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ru.shiftlaboratory.dynamicFeatures.escorts.R

class EscortHomeComponent : Fragment() {

	companion object {
		fun newInstance() = EscortHomeComponent()
	}

	private lateinit var viewModel: EscortHomeComponentViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.escort_home_component_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(EscortHomeComponentViewModel::class.java)
		// TODO: Use the ViewModel
	}
}
