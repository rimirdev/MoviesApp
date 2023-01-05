package com.ryadamir.movieapp.di

import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.remote.RetrofitClient
import org.koin.dsl.module

val dataModule = module {
    single {
        RetrofitClient.instance
    }

    factory {
        Repository(get())
    }
}