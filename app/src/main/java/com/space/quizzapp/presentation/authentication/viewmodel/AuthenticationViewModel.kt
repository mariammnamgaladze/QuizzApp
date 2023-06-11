package com.space.quizzapp.presentation.authentication.viewmodel

import com.space.quizzapp.common.regex.Regex.regex
import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserUseCase
import com.space.quizzapp.data.mapper.toDomainModel
import com.space.quizzapp.presentation.authentication.fragment.AuthenticationFragmentDirections
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizzapp.presentation.model.UserUIModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class AuthenticationViewModel(
    private val saveUserUseCase: SaveUserUseCase,
) : BaseViewModel() {

    private val _errorMessage = MutableSharedFlow<Unit>()
    val errorMessage: SharedFlow<Unit> = _errorMessage


    fun checkUsernameAvailability(username: String) {
        viewModelScope {
            if (isUsernameValid(username)) {
                saveUserInfo(username)
                navigateTo()
            } else
                _errorMessage.emit(Unit)
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        return regex.matches(username)
    }

    private suspend fun saveUserInfo(username: String) {
        val userInfo = UserUIModel(
            username = username,
            isActive = true
        )
        saveUserUseCase.invoke(userInfo.toDomainModel())
    }

    private fun navigateTo() {
        navigate(AuthenticationFragmentDirections.actionStartFragmentToHomeFragment())
    }
}
