package ru.shiftlaboratory.dynamicFeatures.escorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class EscortsFragment : Fragment() {

	companion object {
		fun newInstance() = EscortsFragment()
	}

	private lateinit var viewModel: EscortsViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.escorts_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(EscortsViewModel::class.java)
		// TODO: Use the ViewModel
	}

}
