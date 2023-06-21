package com.space.quizzapp.domain.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.remote.model.QuizItemDTO
import com.space.quizzapp.domain.model.QuizItemDomainModel

class QuizItemDTODomainMapper : ModelMapper<QuizItemDTO, QuizItemDomainModel> {
    override fun invoke(model: QuizItemDTO): QuizItemDomainModel {
        return QuizItemDomainModel(
            id = model.id.toInt(),
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            quizIcon = model.quizIcon,
            questionsCount = model.questionsCount,
            questions = model.questions.map { questionDTO ->
                QuizItemDomainModel.QuizQuestionDomainModel(
                    subjectId = questionDTO.subjectId,
                    questionTitle = questionDTO.questionTitle,
                    questionIndex = questionDTO.questionIndex,
                    correctAnswer = questionDTO.correctAnswer,
                    answers = questionDTO.answers
                )
            }
        )
    }
}