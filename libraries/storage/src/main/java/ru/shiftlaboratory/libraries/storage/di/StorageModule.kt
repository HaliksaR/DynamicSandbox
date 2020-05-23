package ru.shiftlaboratory.libraries.storage.di

import org.koin.dsl.module
import ru.shiftlaboratory.libraries.storage.Storage

val StorageModule = module {
	single { Storage() }
}