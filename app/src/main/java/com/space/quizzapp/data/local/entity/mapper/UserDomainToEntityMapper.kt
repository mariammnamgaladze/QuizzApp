package com.space.quizzapp.data.local.entity.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.local.entity.UserEntityModel
import com.space.quizzapp.domain.model.local.UserDomainModel

class UserDomainToEntityMapper : ModelMapper<UserDomainModel, UserEntityModel> {
    override fun invoke(model: UserDomainModel): UserEntityModel {
        return UserEntityModel(
            id = model.id,
            username = model.username,
            isActive = model.isActive,
            gpa = model.gpa
        )
    }
}