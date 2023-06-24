package com.space.quizzapp.di

import com.space.quizzapp.data.local.entity.mapper.UserDomainToEntityMapper
import com.space.quizzapp.domain.model.local.mapper.UserEntityToDomainMapper
import com.space.quizzapp.domain.model.local.mapper.UserUIToDomainMapper
import com.space.quizzapp.presentation.model.local.mapper.UserDomainToUIMapper
import org.koin.dsl.module

val mapperModule = module {
    single { UserUIToDomainMapper() }
    single { UserDomainToEntityMapper() }
    single { UserEntityToDomainMapper() }
    single { UserDomainToUIMapper() }
    // Other dependencies...
}