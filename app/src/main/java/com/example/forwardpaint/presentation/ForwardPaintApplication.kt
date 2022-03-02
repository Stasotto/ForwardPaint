package com.example.forwardpaint.presentation

import android.app.Application
import com.example.data.di.dataModule
import com.example.forwardpaint.presentation.di.domainModule
import com.example.forwardpaint.presentation.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ForwardPaintApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ForwardPaintApplication)
            modules(
                viewModelsModule,
                domainModule,
                dataModule
            )
        }
    }
}