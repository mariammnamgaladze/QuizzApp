package com.space.quizzapp.presentation.home.viewmodel

import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.*


class HomeViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateUserActiveStatusUseCase: UpdateUserActiveStatusUseCase
) : BaseViewModel()  {

    private val _activeUsernames = MutableSharedFlow<String>()
    val activeUsernames = _activeUsernames.asSharedFlow()

    private var activeUsername: String = ""

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
}