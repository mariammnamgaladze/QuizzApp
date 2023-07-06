package com.space.quizzapp.di

import com.space.quizzapp.data.local.entity.mapper.UserSubjectDomainToEntityMapper
import com.space.quizzapp.data.repository.local.UserRepositoryImp
import com.space.quizzapp.data.repository.local.UserSubjectRepositoryImpl
import com.space.quizzapp.data.repository.remote.QuizRepositoryImpl
import com.space.quizzapp.domain.model.local.mapper.UserSubjectEntityToDomainMapper
import com.space.quizzapp.domain.model.remote.mapper.QuizItemDTODomainMapper
import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.repository.local.UserSubjectRepository
import com.space.quizzapp.domain.repository.remote.QuizRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(userDao = get(), userDomainToEntityMapper = get(), userEntityToDomainMapper =  get()) }
    single<QuizRepository> { QuizRepositoryImpl(apiService = get(), quizItemDTODomainMapper = get()) }
    single<UserSubjectRepository> {
        UserSubjectRepositoryImpl(
            userSubjectDao = get(),
            domainToEntity = get(),
            entityToDomain = get()
        )
    }
}