package com.space.quizzapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizzapp.data.mapper.toUIModel
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserInfoUseCase
import com.space.quizzapp.domain.usecase.user.observe_user_info.ObserveUserByUsernameUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.presentation.model.UIUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeFragmentViewModel(
    private val getCurrentUserInfoUseCase: GetCurrentUserInfoUseCase,
    private val updateUserActiveStatusUseCase: UpdateUserActiveStatusUseCase
) : ViewModel() {
    private val _activeUsernames = MutableSharedFlow<String>()
    val activeUsernames = _activeUsernames.asSharedFlow()

    private var activeUsername: String = ""

    fun getActiveUsernames() {
        viewModelScope.launch(Dispatchers.IO) {
            val activeUser = getCurrentUserInfoUseCase(isActive = true)
            activeUsername = activeUser.username
            _activeUsernames.emit(activeUsername)
        }
    }

    fun updateActiveStatus(isActive: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUserActiveStatusUseCase.updateUserActiveStatus(activeUsername, isActive)
        }
    }
}