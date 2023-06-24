package com.space.quizzapp.presentation.home.viewmodel

import androidx.navigation.NavDirections
import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.common.mapper.ModelMapper
import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.remote.QuizItemDomainModel
import com.space.quizzapp.domain.usecase.quiz.GetQuizUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizzapp.presentation.home.fragment.HomeFragmentDirections
import com.space.quizzapp.presentation.model.local.UserUIModel
import com.space.quizzapp.presentation.model.local.mapper.UserDomainToUIMapper
import com.space.quizzapp.presentation.model.remote.QuizItemUIModel
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateUserActiveStatusUseCase: UpdateUserActiveStatusUseCase,
    private val getQuizUseCase: GetQuizUseCase,
    private val quizItemDomainUIMapper: ModelMapper<QuizItemDomainModel, QuizItemUIModel>,
    private val userDomainToUIMapper: UserDomainToUIMapper
) : BaseViewModel() {

    private val _activeUsernames = MutableStateFlow<UserUIModel?>(null)
    val activeUsernames = _activeUsernames.asStateFlow()

    private var activeUsername: String = ""

    private val _quizItems = MutableStateFlow<List<QuizItemUIModel>>(emptyList())
    val quizItems: StateFlow<List<QuizItemUIModel>> = _quizItems

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getQuiz() {
        viewModelScope {
            _isLoading.value = true
            getQuizUseCase.invoke()
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
            val activeUser = getCurrentUserUseCase.invoke(true)
            _activeUsernames.emit(userDomainToUIMapper(activeUser))
        }
    }

    fun updateActiveStatus(isActive: Boolean) {
        viewModelScope {
            updateUserActiveStatusUseCase.invoke(activeUsername to isActive)
        }
    }

    fun navigateTo(destination: NavDirections) {
        navigate(destination)
    }

    fun navigateToQuiz(item: QuizItemUIModel) {
        navigate(HomeFragmentDirections.actionHomeFragmentToQuestionsFragment(item))
    }

}