package com.space.quizzapp.di

import com.space.quizzapp.domain.usecase.quiz.GetQuizUseCase
import com.space.quizzapp.domain.usecase.subject.GetUserSubjectUseCase
import com.space.quizzapp.domain.usecase.subject.InsertUserSubjectUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.domain.usecase.user.observe_user.ObserveUserUseCase
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.domain.usecase.user.user_validation.UsernameValidationUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    single { SaveUserUseCase(get()) }
    single { ObserveUserUseCase(get()) }
    single { UsernameValidationUseCase(get()) }
    single { GetCurrentUserUseCase(get()) }
    single { UpdateUserActiveStatusUseCase(get()) }
    single { GetQuizUseCase(get()) }
    single { InsertUserSubjectUseCase(get()) }
    single { GetUserSubjectUseCase(get()) }
}