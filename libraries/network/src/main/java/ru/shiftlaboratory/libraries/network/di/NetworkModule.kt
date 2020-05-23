package ru.shiftlaboratory.libraries.network.di

import org.koin.dsl.module
import ru.shiftlaboratory.libraries.network.Network

val NetworkModule = module {
	single { Network() }
}