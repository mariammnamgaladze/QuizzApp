package com.space.quizzapp.presentation.model.local.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.presentation.model.local.UserSubjectUIModel

class UserSubjectDomainToUIMapper : ModelMapper<UserSubjectDomainModel, UserSubjectUIModel> {
    override fun invoke(model: UserSubjectDomainModel): UserSubjectUIModel {
        return UserSubjectUIModel(
            id =model.id,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            quizTitle = model.quizTitle,
            collectedPoints = model.collectedPoints,
            userName = model.userName
        )
    }
}