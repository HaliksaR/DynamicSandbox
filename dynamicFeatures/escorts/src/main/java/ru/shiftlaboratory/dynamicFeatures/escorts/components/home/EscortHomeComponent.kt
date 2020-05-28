package ru.shiftlaboratory.dynamicFeatures.escorts.components.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.escort_home_component_fragment.*
import ru.shiftlaboratory.dynamicFeatures.escorts.R
import ru.shiftlaboratory.dynamicFeatures.providers.escorts.EscortsHomeComponentProviderApi

class EscortHomeComponent : Fragment(), EscortsHomeComponentProviderApi {
	private lateinit var viewModel: EscortHomeComponentViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.escort_home_component_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(EscortHomeComponentViewModel::class.java)
	}

	override fun printHello() {
		city.text = "Hello"
		title.text = "Hello"
		status.text = "Hello"
	}

	override fun get() = this
}
