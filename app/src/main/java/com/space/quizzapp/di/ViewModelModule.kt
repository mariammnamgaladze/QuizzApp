package com.space.quizzapp.di

import com.space.quizzapp.presentation.home.viewmodel.HomeFragmentViewModel
import com.space.quizzapp.presentation.start.viewmodel.AuthenticationFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthenticationFragmentViewModel(get(),get(),get())
    }
    viewModel {
        HomeFragmentViewModel(get(),get())
    }
}