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
        AuthenticationViewModel(saveUserUseCase = get(), userUIToDomainMapper = get())
    }
    viewModel {
        HomeViewModel(
            getCurrentUserUseCase = get(),
            updateUserActiveStatusUseCase = get(),
            getQuizUseCase = get(),
            quizItemDomainUIMapper = get(),
            userDomainToUIMapper = get()
        )
    }
    viewModel {
        DetailsViewModel(
            getCurrentUserUseCase = get(),
            updateUserActiveStatusUseCase = get(),
            getUserSubjectUseCase = get(),
            userSubjectDomainToUIMapper = get()
        )
    }
    viewModel {
        QuestionsViewModel(
            getCurrentUserUseCase = get(),
            insertUserSubjectUseCase = get(),
            mapper = get()
        )
    }
    viewModel {
        SplashViewModel(getActiveUserUseCase = get())
    }
}