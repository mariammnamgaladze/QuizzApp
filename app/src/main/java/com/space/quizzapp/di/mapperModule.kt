package com.space.quizzapp.di

import com.space.quizzapp.data.local.entity.mapper.UserDomainToEntityMapper
import com.space.quizzapp.data.local.entity.mapper.UserSubjectDomainToEntityMapper
import com.space.quizzapp.domain.model.local.mapper.UserEntityToDomainMapper
import com.space.quizzapp.domain.model.local.mapper.UserSubjectEntityToDomainMapper
import com.space.quizzapp.domain.model.local.mapper.UserUIToDomainMapper
import com.space.quizzapp.domain.model.remote.mapper.QuizItemDTODomainMapper
import com.space.quizzapp.presentation.model.local.mapper.UserDomainToUIMapper
import com.space.quizzapp.presentation.model.local.mapper.UserSubjectDomainToUIMapper
import com.space.quizzapp.presentation.model.local.mapper.UserSubjectUIToDomainMapper
import com.space.quizzapp.presentation.model.remote.mapper.QuizItemDomainUIMapper
import org.koin.dsl.module

val mapperModule = module {
    single { UserUIToDomainMapper() }
    single { QuizItemDTODomainMapper() }
    single { UserSubjectDomainToEntityMapper() }
    single { UserDomainToEntityMapper() }
    single { UserEntityToDomainMapper() }
    single { UserDomainToUIMapper() }
    single { QuizItemDomainUIMapper() }
    single { UserSubjectDomainToUIMapper() }
    single { UserSubjectUIToDomainMapper() }
    single { UserSubjectEntityToDomainMapper() }
}