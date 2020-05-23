package ru.shiftlaboratory.libraries.storage

import android.util.Log

class Storage {
	fun sendToStorage(message: String) {
		postMessage(message)
	}

	private fun postMessage(message: String) {
		Log.d("STORAGE", message)
	}
}