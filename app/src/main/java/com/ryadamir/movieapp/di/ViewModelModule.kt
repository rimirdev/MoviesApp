package com.ryadamir.movieapp.di

import com.ryadamir.movieapp.data.Repository
import com.ryadamir.movieapp.ui.detail.DetailViewModel
import com.ryadamir.movieapp.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DetailViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}