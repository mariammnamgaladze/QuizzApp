package com.space.quizzapp.presentation.model.remote.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.domain.model.remote.QuizItemDomainModel
import com.space.quizzapp.presentation.model.remote.QuizItemUIModel

class QuizItemDomainUIMapper : ModelMapper<QuizItemDomainModel, QuizItemUIModel> {
    override fun invoke(model: QuizItemDomainModel): QuizItemUIModel = with(model) {
        QuizItemUIModel(
            id = id,
            quizTitle = quizTitle,
            quizDescription = quizDescription,
            quizIcon = quizIcon,
            questionsCount = questionsCount,
            questions = questions.map { question ->
                with(question) {
                    QuizItemUIModel.QuizQuestionUIModel(
                        subjectId = subjectId,
                        questionTitle = questionTitle,
                        questionIndex = questionIndex,
                        correctAnswer = correctAnswer,
                        answers = answers
                    )
                }
            }
        )
    }
}
