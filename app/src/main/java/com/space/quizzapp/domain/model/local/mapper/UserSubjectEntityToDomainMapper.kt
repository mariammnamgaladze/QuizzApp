package com.space.quizzapp.domain.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel

class UserSubjectEntityToDomainMapper :
    ModelMapper<UserSubjectEntityModel, UserSubjectDomainModel> {
    override fun invoke(model: UserSubjectEntityModel): UserSubjectDomainModel {
        return UserSubjectDomainModel(
            id =model.id,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            quizTitle = model.quizTitle,
            collectedPoints = model.collectedPoints,
            userName = model.userName
        )
    }
}
