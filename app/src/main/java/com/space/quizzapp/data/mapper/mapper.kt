package com.space.quizzapp.data.mapper

import com.space.quizzapp.data.local.entity.UserEntity
import com.space.quizzapp.domain.model.DomainUserModel
import com.space.quizzapp.presentation.model.UIUserModel

fun DomainUserModel.toEntity() = UserEntity(
    id = id,
    username = username,
    isActive = isActive

)

fun UIUserModel.toDomainModel() = DomainUserModel(
    id = id,
    username = username,
    isActive = isActive
)

fun UserEntity.toDomainModel() = DomainUserModel(
    id = id,
    username = username,
    isActive = isActive

)

fun DomainUserModel.toUIModel() = UIUserModel(
    id = id,
    username = username,
    isActive = isActive

)