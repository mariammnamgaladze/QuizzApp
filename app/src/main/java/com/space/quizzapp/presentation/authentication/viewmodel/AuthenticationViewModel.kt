package com.space.quizzapp.presentation.authentication.viewmodel

import com.space.quizzapp.R
import com.space.quizzapp.common.exception.InvalidUsernameException
import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserUseCase
import com.space.quizzapp.domain.usecase.user.user_validation.UsernameValidationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.space.quizzapp.common.resource.Result
import com.space.quizzapp.data.mapper.toDomainModel
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.presentation.authentication.fragment.AuthenticationFragmentDirections
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizzapp.presentation.model.UserUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val usernameValidationUseCase: UsernameValidationUseCase,
    private val updateUserActiveStatusUseCase: UpdateUserActiveStatusUseCase
) : BaseViewModel() {

    private val _usernameAvailability = MutableStateFlow<Result<Boolean>>(Result.Success(true))
    val usernameAvailability: StateFlow<Result<Boolean>> = _usernameAvailability

    fun checkUsernameAvailability(username: String) {
        viewModelScope {
            if (isUsernameValid(username)) {
                val isUsernameAvailable = withContext(Dispatchers.IO) {
                    usernameValidationUseCase.isUsernameAvailable(username)
                }
                _usernameAvailability.value = Result.Success(isUsernameAvailable)
                navigateTo()

                if (isUsernameAvailable) {
                    saveUserInfo(username)
                }
            } else {
                _usernameAvailability.value =
                    Result.Error(InvalidUsernameException(R.string.incorrect_input.toString()))
            }
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        val regex = Regex("^[a-zA-Z][a-zA-Z0-9]{7,19}$")
        return regex.matches(username)
    }

    private fun saveUserInfo(username: String) {
        if (username.isNotEmpty()) {
            viewModelScope {
                val userInfo = UserUIModel(
                    username = username,
                    isActive = true
                )
                saveUserUseCase.invoke(userInfo.toDomainModel())
            }
        }
    }

    suspend fun updateUserActiveStatus(username: String, isActive: Boolean) {
        withContext(Dispatchers.IO) {
            updateUserActiveStatusUseCase.updateUserActiveStatus(username, isActive)
        }
    }

    private fun navigateTo() {
        navigate(AuthenticationFragmentDirections.actionStartFragmentToHomeFragment())
    }
}
