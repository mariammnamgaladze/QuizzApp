package com.space.quizzapp.domain.model

data class QuizQuestionDomainModel(
    val subjectId: Int?,
    val questionTitle: String?,
    val questionIndex: Int?,
    val correctAnswer: String?,
    val answers: List<String> = emptyList(),
)