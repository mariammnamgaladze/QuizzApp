package com.space.quizzapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizItemUIModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
    val questions: List<QuizQuestionUIModel>
):Parcelable
{
    @Parcelize
    data class QuizQuestionUIModel(
        val subjectId: Int,
        var questionTitle: String,
        val questionIndex: Int,
        val correctAnswer: String,
        val answers: List<String>,
    ):Parcelable
}