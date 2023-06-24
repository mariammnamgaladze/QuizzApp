package com.space.quizzapp.data.local.entity.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel

class UserSubjectDomainToEntityMapper :
    ModelMapper<UserSubjectDomainModel, UserSubjectEntityModel> {
    override fun invoke(model: UserSubjectDomainModel): UserSubjectEntityModel = with(model) {
        UserSubjectEntityModel(
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
