package com.space.quizzapp.domain.model.remote.mapper

import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.data.remote.model.QuizItemDTO
import com.space.quizzapp.domain.model.remote.QuizItemDomainModel

class QuizItemDTODomainMapper : ModelMapper<QuizItemDTO, QuizItemDomainModel> {
    override fun invoke(model: QuizItemDTO): QuizItemDomainModel = with(model) {
        QuizItemDomainModel(
            id = id.toInt(),
            quizTitle = quizTitle,
            quizDescription = quizDescription,
            quizIcon = quizIcon,
            questionsCount = questionsCount,
            questions = questions.map { questionDTO ->
                with(questionDTO) {
                    QuizItemDomainModel.QuizQuestionDomainModel(
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
