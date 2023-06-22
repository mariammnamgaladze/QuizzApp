package com.space.quizzapp.di

import com.space.quizzapp.data.repository.local.UserRepositoryImp
import com.space.quizzapp.domain.repository.local.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(get()) }
}