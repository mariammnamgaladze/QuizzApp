package com.space.quizzapp.domain.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.local.UserDomainModel
import com.space.quizzapp.presentation.model.local.UserUIModel

class UserUIToDomainMapper : ModelMapper<UserUIModel, UserDomainModel> {
    override fun invoke(model: UserUIModel): UserDomainModel = with(model) {
        UserDomainModel(
            id = id,
            username = username,
            isActive = isActive,
            gpa = gpa
        )
    }
}
