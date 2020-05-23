package ru.shiftlaboratory.libraries.network

import android.util.Log

class Network {
	fun sendToInternet(message: String) {
		postMessage(message)
	}

	private fun postMessage(message: String) {
		Log.d("NETWORK", message)
	}
}