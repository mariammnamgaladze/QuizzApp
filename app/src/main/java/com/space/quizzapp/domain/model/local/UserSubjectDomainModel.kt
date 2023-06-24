package com.space.quizzapp.domain.model.local

data class UserSubjectDomainModel(
    val id: Int,
    val quizDescription: String,
    val quizIcon: String,
    val quizTitle: String,
    val collectedPoints: Int,
    val userName: String,
    val questionsCount: Int,
)