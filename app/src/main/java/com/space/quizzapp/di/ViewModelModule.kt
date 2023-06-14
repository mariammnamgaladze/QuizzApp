package com.space.quizzapp.di

import com.space.quizzapp.presentation.home.viewmodel.HomeViewModel
import com.space.quizzapp.presentation.authentication.viewmodel.AuthenticationViewModel
import com.space.quizzapp.presentation.detail.viewmodel.DetailsViewModel
import com.space.quizzapp.presentation.model.mapper.QuizItemDomainUIMapper
import com.space.quizzapp.presentation.question.viewmodel.QuestionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthenticationViewModel(get())
    }
    viewModel {
        HomeViewModel(get(),get(),get(),QuizItemDomainUIMapper())
    }
    viewModel {
        DetailsViewModel()
    }
    viewModel {
        QuestionsViewModel()
    }

}