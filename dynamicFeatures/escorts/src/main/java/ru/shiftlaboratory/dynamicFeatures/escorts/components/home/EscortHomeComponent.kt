package ru.shiftlaboratory.dynamicFeatures.escorts.components.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.escort_home_component_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.shiftlaboratory.dynamicFeatures.escorts.R
import ru.shiftlaboratory.dynamicFeatures.providers.escorts.EscortsHomeComponentProviderApi

class EscortHomeComponent : Fragment(), EscortsHomeComponentProviderApi {
	private val viewModel: EscortHomeComponentViewModel by viewModel()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.escort_home_component_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.apply { 0 }
	}

	override fun printHello() {
		city.text = "Hello"
		title.text = "Hello"
		status.text = "Hello"
	}

	override fun get() = this
}
