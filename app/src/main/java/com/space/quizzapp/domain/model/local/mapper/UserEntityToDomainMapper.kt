package com.space.quizzapp.domain.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.local.entity.UserEntityModel
import com.space.quizzapp.domain.model.local.UserDomainModel

class UserEntityToDomainMapper : ModelMapper<UserEntityModel, UserDomainModel> {
    override fun invoke(model: UserEntityModel): UserDomainModel {
        return UserDomainModel(
            id = model.id,
            username = model.username,
            isActive = model.isActive,
            gpa = model.gpa
        )
    }
}