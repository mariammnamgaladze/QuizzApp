package com.space.quizzapp.di

import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserInfoUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserInfoUseCaseImpl
import com.space.quizzapp.domain.usecase.user.observe_user_info.ObserveUserByUsernameUseCase
import com.space.quizzapp.domain.usecase.user.observe_user_info.ObserveUserByUsernameUseCaseImpl
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserInfoUseCase
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserInfoUseCaseImpl
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCaseImpl
import com.space.quizzapp.domain.usecase.user.user_validation.UsernameValidationUseCase
import com.space.quizzapp.domain.usecase.user.user_validation.UsernameValidationUseCaseImpl
import org.koin.dsl.module

val UseCaseModule = module {
    single<SaveUserInfoUseCase> { SaveUserInfoUseCaseImpl(get()) }
    single<ObserveUserByUsernameUseCase> {ObserveUserByUsernameUseCaseImpl(get()) }
    single<UsernameValidationUseCase> {UsernameValidationUseCaseImpl(get()) }
    single<GetCurrentUserInfoUseCase> { GetCurrentUserInfoUseCaseImpl(get()) }
    single<UpdateUserActiveStatusUseCase> { UpdateUserActiveStatusUseCaseImpl(get()) }
}