package com.space.quizzapp.presentation.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.presentation.model.local.UserSubjectUIModel

class UserSubjectDomainToUIMapper : ModelMapper<UserSubjectDomainModel, UserSubjectUIModel> {
    override fun invoke(model: UserSubjectDomainModel): UserSubjectUIModel = with(model) {
        UserSubjectUIModel(
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
