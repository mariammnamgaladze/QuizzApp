package com.space.quizzapp.presentation.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.local.UserDomainModel
import com.space.quizzapp.presentation.model.local.UserUIModel

class UserDomainToUIMapper : ModelMapper<UserDomainModel, UserUIModel> {
    override fun invoke(model: UserDomainModel): UserUIModel = with(model) {
        UserUIModel(
            id = id,
            username = username,
            isActive = isActive,
            gpa = gpa
        )
    }
}
