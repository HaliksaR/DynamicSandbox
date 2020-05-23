package ru.shiftlaboratory.app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.getViewModel
import ru.shiftlaboratory.app.R

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: AppViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		Navigation.findNavController(this, R.id.host_global)
		viewModel = getViewModel()
	}
}
