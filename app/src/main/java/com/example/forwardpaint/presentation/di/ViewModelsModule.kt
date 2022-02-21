package com.example.forwardpaint.presentation.di

import com.example.forwardpaint.presentation.viewmodels.MainFragViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        MainFragViewModel()
    }
}