package com.space.quizzapp.presentation.question.viewmodel

import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizzapp.presentation.model.QuizItemUIModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class QuestionsViewModel() : BaseViewModel() {

    private val _quizItem = MutableSharedFlow<QuizItemUIModel.QuizQuestionUIModel>()
    val quizItem = _quizItem.asSharedFlow()

    private var currentQuestionIndex = 0
    lateinit var quizModel: QuizItemUIModel
    private var correctAnswerCount = 0

    fun updateCorrectPoints(isAnswerCorrect: Boolean) {
        if (isAnswerCorrect) correctAnswerCount++
    }

    fun getQuiz() {
        if (currentQuestionIndex < quizModel.questions.size) {
            viewModelScope {
                _quizItem.emit(quizModel.questions[currentQuestionIndex])
                currentQuestionIndex++
            }
        }
    }
}