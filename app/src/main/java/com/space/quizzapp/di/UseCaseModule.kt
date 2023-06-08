package com.space.quizzapp.di

import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCaseImpl
import com.space.quizzapp.domain.usecase.user.observe_user.ObserveUserUseCase
import com.space.quizzapp.domain.usecase.user.observe_user.ObserveUserUseCaseImpl
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserUseCase
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserUseCaseImpl
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCaseImpl
import com.space.quizzapp.domain.usecase.user.user_validation.UsernameValidationUseCase
import com.space.quizzapp.domain.usecase.user.user_validation.UsernameValidationUseCaseImpl
import org.koin.dsl.module

val UseCaseModule = module {
    single<SaveUserUseCase> { SaveUserUseCaseImpl(get()) }
    single<ObserveUserUseCase> {ObserveUserUseCaseImpl(get()) }
    single<UsernameValidationUseCase> {UsernameValidationUseCaseImpl(get()) }
    single<GetCurrentUserUseCase> { GetCurrentUserUseCaseImpl(get()) }
    single<UpdateUserActiveStatusUseCase> { UpdateUserActiveStatusUseCaseImpl(get()) }
}