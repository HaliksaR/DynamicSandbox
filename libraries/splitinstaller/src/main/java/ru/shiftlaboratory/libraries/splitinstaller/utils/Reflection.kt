package ru.shiftlaboratory.libraries.splitinstaller.utils

object Reflection {
	fun <T> loadView(nameAndPackage: String): T? =
		try {
			Class.forName(nameAndPackage) as T
		} catch (e: ClassNotFoundException) {
			null
		}
}