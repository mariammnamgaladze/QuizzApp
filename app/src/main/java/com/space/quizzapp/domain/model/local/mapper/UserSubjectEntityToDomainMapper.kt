package com.space.quizzapp.domain.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel

class UserSubjectEntityToDomainMapper :
    ModelMapper<UserSubjectEntityModel, UserSubjectDomainModel> {
    override fun invoke(model: UserSubjectEntityModel): UserSubjectDomainModel = with(model) {
        UserSubjectDomainModel(
            id = id,
            quizDescription = quizDescription,
            quizIcon = quizIcon,
            quizTitle = quizTitle,
            collectedPoints = collectedPoints,
            userName = userName,
            questionsCount = questionsCount
        )
    }
}
