package com.ryadamir.movieapp.di

import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.model.favorite.FavoriteDatabase
import com.ryadamir.movieapp.remote.Api
import com.ryadamir.movieapp.remote.RetrofitClient
import org.koin.dsl.module

val dataModule = module {
    single {
        RetrofitClient.instance
    }
    single {
        FavoriteDatabase.getInstance(get())
    }
    factory {
        Repository(get<Api>(), get<FavoriteDatabase>())
    }
}