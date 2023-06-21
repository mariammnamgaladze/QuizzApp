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
            questionsCount = model.questionsCount,
            questions = model.questions.map { question ->
                QuizItemUIModel.QuizQuestionUIModel(
                    subjectId = question.subjectId,
                    questionTitle = question.questionTitle,
                    questionIndex = question.questionIndex,
                    correctAnswer = question.correctAnswer,
                    answers = question.answers
                )
            }
        )
    }
}
