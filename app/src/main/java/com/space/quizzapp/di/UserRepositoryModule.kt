package com.space.quizzapp.di

import com.space.quizzapp.data.repository.local.UserRepositoryImp
import com.space.quizzapp.data.repository.remote.QuizRepositoryImpl
import com.space.quizzapp.domain.mapper.QuizItemDTODomainMapper
import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.repository.remote.QuizRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(get()) }
    single<QuizRepository> { QuizRepositoryImpl(get(),QuizItemDTODomainMapper())}
}