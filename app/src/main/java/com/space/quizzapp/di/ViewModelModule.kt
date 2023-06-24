package com.space.quizzapp.di

import com.space.quizzapp.presentation.home.viewmodel.HomeViewModel
import com.space.quizzapp.presentation.authentication.viewmodel.AuthenticationViewModel
import com.space.quizzapp.presentation.detail.viewmodel.DetailsViewModel
import com.space.quizzapp.presentation.model.local.mapper.UserSubjectDomainToUIMapper
import com.space.quizzapp.presentation.model.local.mapper.UserSubjectUIToDomainMapper
import com.space.quizzapp.presentation.model.remote.mapper.QuizItemDomainUIMapper
import com.space.quizzapp.presentation.question.viewmodel.QuestionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthenticationViewModel(get())
    }
    viewModel {
        HomeViewModel(get(), get(), get(), QuizItemDomainUIMapper())
    }
    viewModel {
        DetailsViewModel(get(),get(),get(),UserSubjectDomainToUIMapper())
    }
    viewModel {
        QuestionsViewModel(get(),get(), UserSubjectUIToDomainMapper())
    }

}