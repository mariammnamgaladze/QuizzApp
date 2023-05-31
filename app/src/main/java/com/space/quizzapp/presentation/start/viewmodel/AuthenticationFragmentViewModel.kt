package com.space.quizzapp.presentation.start.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizzapp.common.exception.InvalidUsernameException
import com.space.quizzapp.domain.usecase.user.save_user.SaveUserInfoUseCase
import com.space.quizzapp.domain.usecase.user.user_validation.UsernameValidationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.space.quizzapp.common.resource.Result
import com.space.quizzapp.data.mapper.toDomainModel
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.presentation.model.UIUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationFragmentViewModel(
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val usernameValidationUseCase: UsernameValidationUseCase,
    private val updateUserActiveStatusUseCase: UpdateUserActiveStatusUseCase
) : ViewModel() {

    private val _usernameAvailability = MutableStateFlow<Result<Boolean>>(Result.Success(true))
    val usernameAvailability: StateFlow<Result<Boolean>> = _usernameAvailability

    fun checkUsernameAvailability(username: String) {
        viewModelScope.launch {
            _usernameAvailability.value = Result.Loading

            if (isUsernameValid(username)) {
                val isUsernameAvailable = withContext(Dispatchers.IO) {
                    usernameValidationUseCase.isUsernameAvailable(username)
                }
                _usernameAvailability.value = Result.Success(isUsernameAvailable)

                if (isUsernameAvailable) {
                    saveUserInfo(username)
                }
            } else {
                _usernameAvailability.value =
                    Result.Error(InvalidUsernameException("Invalid username format"))
            }
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        val regex = Regex("^[a-zA-Z][a-zA-Z0-9]{7,19}$")
        return regex.matches(username)
    }

    private fun saveUserInfo(username: String) {
        if (username.isNotEmpty()) {
            viewModelScope.launch {
                val userInfo = UIUserModel(
                    username = username,
                    isActive = true
                )
                saveUserInfoUseCase.invoke(userInfo.toDomainModel())
            }
        }
    }

    suspend fun updateUserActiveStatus(username: String, isActive: Boolean) {
        withContext(Dispatchers.IO) {
            updateUserActiveStatusUseCase.updateUserActiveStatus(username, isActive)
        }
    }

}
