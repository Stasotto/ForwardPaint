package com.example.data.di

import com.example.data.storage.DatabaseStorage
import com.example.data.storage.DatabaseStorageImpl
import com.example.data.storage.firebase.Firebase
import org.koin.dsl.module

val dataModule = module {
    single<DatabaseStorage> {
        DatabaseStorageImpl(firebase = get())
    }

    single {
        Firebase()
    }
}