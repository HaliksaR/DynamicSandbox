package ru.shiftlaboratory.libraries.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(navCommand: NavCommand, hostId: Int? = null) {
	if (hostId == null) {
		findNavController()
	} else {
		Navigation.findNavController(requireActivity(), hostId)
	}.navigate(navCommand.action, navCommand.args, navCommand.navOptions)
}