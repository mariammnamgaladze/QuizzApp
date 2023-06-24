package com.space.quizzapp.data.local.entity.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel

class UserSubjectDomainToEntityMapper :
    ModelMapper<UserSubjectDomainModel, UserSubjectEntityModel> {
    override fun invoke(model: UserSubjectDomainModel): UserSubjectEntityModel {
        return UserSubjectEntityModel(
            id = model.id,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            quizTitle = model.quizTitle,
            collectedPoints = model.collectedPoints,
            userName = model.userName
        )
    }
}
