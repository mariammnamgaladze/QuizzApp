package com.space.quizzapp.domain.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.remote.model.QuizItemDTO
import com.space.quizzapp.domain.model.QuizItemDomainModel


class QuizItemMapper : ModelMapper<QuizItemDTO, QuizItemDomainModel> {
    override fun invoke(model: QuizItemDTO): QuizItemDomainModel {
        return QuizItemDomainModel(
            id = model.id?.toIntOrNull(),
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount
        )
    }
}