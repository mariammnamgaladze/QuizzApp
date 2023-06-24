package com.space.quizzapp.presentation.question.viewmodel

import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.domain.usecase.subject.InsertUserSubjectUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizzapp.presentation.model.local.UserSubjectUIModel
import com.space.quizzapp.presentation.model.local.mapper.UserSubjectUIToDomainMapper
import com.space.quizzapp.presentation.model.remote.QuizItemUIModel
import com.space.quizzapp.presentation.question.fragment.QuestionsFragmentDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuestionsViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val insertUserSubjectUseCase: InsertUserSubjectUseCase,
    private val mapper: UserSubjectUIToDomainMapper
) : BaseViewModel() {

    private val _quizItem = MutableStateFlow<QuizItemUIModel.QuizQuestionUIModel?>(null)
    val quizItem = _quizItem.asStateFlow()

    private val _finalScore = MutableStateFlow<Int?>(null)
    val finalScore = _finalScore.asStateFlow()


    private var currentQuestionIndex = 0
    lateinit var quizModel: QuizItemUIModel
    private var correctAnswerCount = 0


    fun updateCorrectPoints(isAnswerCorrect: Boolean) {
        viewModelScope {
            if (isAnswerCorrect) correctAnswerCount++
        }
    }

    fun getQuiz() {
        viewModelScope {
            if (currentQuestionIndex < quizModel.questions.size) {
                _quizItem.emit(quizModel.questions[currentQuestionIndex])
                currentQuestionIndex++

            } else {
                saveScore()
                _finalScore.emit(correctAnswerCount)
            }
        }
    }

    private fun saveScore() {
        viewModelScope {
            val activeUser = getCurrentUserUseCase.invoke(true)
            val userSubject = with(quizModel) {
                UserSubjectUIModel(
                    id,
                    quizDescription,
                    quizIcon,
                    quizTitle,
                    correctAnswerCount,
                    activeUser.username,
                    questionsCount
                )
            }
            insertUserSubjectUseCase.invoke(mapper(userSubject))
        }
    }

    fun navigateToHome() {
        navigate(QuestionsFragmentDirections.actionQuestionsFragmentToHomeFragment())
    }


}