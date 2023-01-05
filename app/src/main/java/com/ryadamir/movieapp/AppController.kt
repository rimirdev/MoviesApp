package com.ryadamir.movieapp

import android.app.Application
import com.ryadamir.movieapp.di.dataModule
import com.ryadamir.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppController)
            modules(dataModule)
            modules(viewModelModule)
        }
    }
}