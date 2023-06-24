package com.space.quizzapp.presentation.model.local

data class UserSubjectUIModel(
    val id:Int,
    val quizDescription: String,
    val quizIcon: String,
    val quizTitle: String,
    val collectedPoints: Int,
    val userName: String
)