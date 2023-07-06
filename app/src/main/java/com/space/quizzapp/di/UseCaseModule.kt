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
    single { UpdateUserActiveStatusUseCase(userRepository = get()) }
    single { SaveUserUseCase(userRepository = get()) }
    single { GetCurrentUserUseCase(userRepository = get()) }
    single { GetQuizUseCase(quizRepository = get()) }
    single { InsertUserSubjectUseCase(userSubjectRepository = get(), updateGpaUseCase = get()) }
    single { GetUserSubjectUseCase(userSubjectRepository = get()) }
    single { UpdateGpaUseCase(userRepository = get(), getUserSubjectUseCase = get()) }
    single { GetActiveUserUseCase(userRepository = get()) }
}