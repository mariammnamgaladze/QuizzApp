package com.space.quizzapp.di

import com.space.quizzapp.presentation.authentication.viewmodel.AuthenticationViewModel
import com.space.quizzapp.presentation.detail.viewmodel.DetailsViewModel
import com.space.quizzapp.presentation.home.viewmodel.HomeViewModel
import com.space.quizzapp.presentation.question.viewmodel.QuestionsViewModel
import com.space.quizzapp.presentation.splash.viewmode.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthenticationViewModel(get(), get())
    }
    viewModel {
        HomeViewModel(get(), get(), get(), get(), get())
    }
    viewModel {
        DetailsViewModel(get(), get(), get(), get())
    }
    viewModel {
        QuestionsViewModel(get(), get(), get())
    }
    viewModel {
        SplashViewModel(get())
    }
}