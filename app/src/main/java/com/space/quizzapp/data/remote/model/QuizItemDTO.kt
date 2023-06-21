package com.space.quizzapp.data.remote.model

data class QuizItemDTO(
    val id: String,
    val questionsCount: Int,
    val quizDescription: String,
    val quizIcon: String,
    val quizTitle: String,
    val questions: List<QuizQuestionDTO>
) {
    data class QuizQuestionDTO(
        val answers: List<String>,
        val correctAnswer: String,
        val questionIndex: Int,
        val questionTitle: String,
        val subjectId: Int
    )
}