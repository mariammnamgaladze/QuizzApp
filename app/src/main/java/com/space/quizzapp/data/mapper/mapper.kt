package com.space.quizzapp.data.mapper

import com.space.quizzapp.data.local.entity.UserEntityModel
import com.space.quizzapp.domain.model.UserDomainModel
import com.space.quizzapp.presentation.model.UserUIModel

fun UserDomainModel.toEntity() = UserEntityModel(
    id = id,
    username = username,
    isActive = isActive
)

fun UserUIModel.toDomainModel() = UserDomainModel(
    id = id,
    username = username,
    isActive = isActive
)

fun UserEntityModel.toDomainModel() = UserDomainModel(
    id = id,
    username = username,
    isActive = isActive
)

fun UserDomainModel.toUIModel() = UserUIModel(
    id = id,
    username = username,
    isActive = isActive
)