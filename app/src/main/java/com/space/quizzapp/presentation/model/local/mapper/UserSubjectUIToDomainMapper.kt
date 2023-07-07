package com.space.quizzapp.presentation.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.presentation.model.local.UserSubjectUIModel

class UserSubjectUIToDomainMapper : ModelMapper<UserSubjectUIModel, UserSubjectDomainModel> {
    override fun invoke(model: UserSubjectUIModel): UserSubjectDomainModel = with(model) {
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
