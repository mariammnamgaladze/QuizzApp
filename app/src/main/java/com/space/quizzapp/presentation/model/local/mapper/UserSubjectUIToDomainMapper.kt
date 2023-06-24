package com.space.quizzapp.presentation.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.presentation.model.local.UserSubjectUIModel

class UserSubjectUIToDomainMapper : ModelMapper<UserSubjectUIModel, UserSubjectDomainModel> {
    override fun invoke(model: UserSubjectUIModel): UserSubjectDomainModel {
        return UserSubjectDomainModel(
            id = model.id,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            quizTitle = model.quizTitle,
            collectedPoints = model.collectedPoints,
            userName = model.userName
        )
    }
}
