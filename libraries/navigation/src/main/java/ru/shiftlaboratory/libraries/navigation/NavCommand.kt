package ru.shiftlaboratory.libraries.navigation

import android.os.Bundle
import androidx.navigation.NavOptions

data class NavCommand(
	val action: Int,
	var args: Bundle? = null,
	var navOptions: NavOptions? = null
)
