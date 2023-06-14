package com.space.quizzapp.presentation.model.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.QuizItemDomainModel
import com.space.quizzapp.presentation.model.QuizItemUIModel

class QuizItemDomainUIMapper : ModelMapper<QuizItemDomainModel, QuizItemUIModel> {
    override fun invoke(model: QuizItemDomainModel): QuizItemUIModel {
        return QuizItemUIModel(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount
        )
    }
}