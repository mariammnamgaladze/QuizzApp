package com.space.quizzapp.presentation.home.viewmodel

import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.QuizItemDomainModel
import com.space.quizzapp.domain.usecase.quiz.GetQuizUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.presentation.authentication.fragment.AuthenticationFragmentDirections
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizzapp.presentation.home.fragment.HomeFragmentDirections
import com.space.quizzapp.presentation.model.QuizItemUIModel
import kotlinx.coroutines.flow.*


class HomeViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateUserActiveStatusUseCase: UpdateUserActiveStatusUseCase,
    private val getQuizUseCase: GetQuizUseCase,
    private val quizItemDomainUIMapper: ModelMapper<QuizItemDomainModel, QuizItemUIModel>
) : BaseViewModel() {

    private val _activeUsernames = MutableSharedFlow<String>()
    val activeUsernames = _activeUsernames.asSharedFlow()

    private var activeUsername: String = ""

    private val _quizItems = MutableStateFlow<List<QuizItemUIModel>>(emptyList())
    val quizItems: StateFlow<List<QuizItemUIModel>> = _quizItems

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getQuizQuestions() {
        viewModelScope {
            _isLoading.value = true
            getQuizUseCase.execute()
                .onEach { responseHandler ->
                    when (responseHandler) {
                        is ResponseHandler.Success -> {
                            val quizItems =
                                responseHandler.response.map { quizItemDomainUIMapper(it) }
                            _quizItems.value = quizItems
                            _isLoading.value = false
                        }
                        is ResponseHandler.Error -> {
                            _error.value = responseHandler.errorResponse
                            _isLoading.value = false
                        }
                        is ResponseHandler.InProcess -> {
                            _isLoading.value = true
                        }
                    }
                }
                .catch { exception ->
                    _isLoading.value = false
                    _error.value = exception.message
                }
                .collect()
        }
    }


    fun getActiveUsernames() {
        viewModelScope {
            val activeUser = getCurrentUserUseCase(isActive = true)
            activeUsername = activeUser.username
            _activeUsernames.emit(activeUsername)
        }
    }

    fun updateActiveStatus(isActive: Boolean) {
        viewModelScope {
            updateUserActiveStatusUseCase.updateUserActiveStatus(activeUsername, isActive)
        }
    }

     fun navigateToDetails() {
        navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
    }
     fun navigateToStart() {
        navigate(HomeFragmentDirections.actionHomeFragmentToStartFragment())
    }
     fun navigateToQuiz() {
        navigate(HomeFragmentDirections.actionHomeFragmentToQuestionsFragment())
    }
}