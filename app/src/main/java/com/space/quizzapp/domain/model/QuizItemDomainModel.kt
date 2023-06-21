package com.space.quizzapp.domain.model


data class QuizItemDomainModel(
    val id: Int,
    val quizTitle: String,
    val quizDescription: String,
    val quizIcon: String,
    val questionsCount: Int,
    val questions: List<QuizQuestionDomainModel>
){
    data class QuizQuestionDomainModel(
        val subjectId: Int,
        val questionTitle: String,
        val questionIndex: Int,
        val correctAnswer: String,
        val answers: List<String>,
        )
}
