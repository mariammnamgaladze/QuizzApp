package com.space.quizzapp.domain.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.local.entity.UserEntityModel
import com.space.quizzapp.domain.model.local.UserDomainModel

class UserEntityToDomainMapper : ModelMapper<UserEntityModel, UserDomainModel> {
    override fun invoke(model: UserEntityModel): UserDomainModel = with(model) {
        UserDomainModel(
            id = id,
            username = username,
            isActive = isActive,
            gpa = gpa
        )
    }
}
