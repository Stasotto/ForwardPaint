package com.example.forwardpaint.presentation.di

import com.example.data.UserRepositoryImpl
import com.example.domain.*
import com.example.domain.repository.UserRepository
import org.koin.dsl.module

val domainModule = module {

    single<UserRepository> {
        UserRepositoryImpl(databaseStorage = get())
    }
    single {
        SaveMessageUseCase(repository = get())
    }
    single<GetMessage> {
        GetMessagesUseCase(repository = get())
    }
    single {
        GetOrdersUseCase(repository = get())
    }
    single {
        SaveOrderUseCase(repository = get())
    }
}