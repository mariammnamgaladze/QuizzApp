package com.space.quizzapp.presentation.splash.viewmode

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.domain.usecase.user.active.GetActiveUserUseCase
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashViewModel(
    private val getActiveUserUseCase: GetActiveUserUseCase
) : BaseViewModel() {
    private val _homeDestinationFlow = MutableStateFlow(0)
    val homeDestinationFlow: StateFlow<Int> = _homeDestinationFlow.asStateFlow()

    fun checkIfUserIsLoggedIn() {
        viewModelScope {
            val user = getActiveUserUseCase()
            if (user != null) {
                _homeDestinationFlow.value = R.id.action_splashFragment_to_homeFragment
            } else {
                _homeDestinationFlow.value = R.id.action_splashFragment_to_startFragment
            }
        }
    }
}