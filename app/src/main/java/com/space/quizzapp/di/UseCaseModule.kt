package com.space.quizzapp.di

import com.space.quizzapp.domain.usecase.quiz.GetQuizUseCase
import com.space.quizzapp.domain.usecase.subject.GetUserSubjectUseCase
import com.space.quizzapp.domain.usecase.subject.InsertUserSubjectUseCase
import com.space.quizzapp.domain.usecase.user.active.GetActiveUserUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.domain.usecase.user.gpa_usecase.UpdateGpaUseCase
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    single { UpdateUserActiveStatusUseCase(get()) }
    single { SaveUserUseCase(get()) }
    single { GetCurrentUserUseCase(get()) }
    single { GetQuizUseCase(get()) }
    single { InsertUserSubjectUseCase(get(), get()) }
    single { GetUserSubjectUseCase(get()) }
    single { UpdateGpaUseCase(get(), get()) }
    single { GetActiveUserUseCase(get()) }
}