package com.space.quizzapp.presentation.splash.viewmode

import android.util.Log
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.domain.usecase.user.active.GetActiveUserUseCase
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SplashViewModel(
    private val getActiveUserUseCase: GetActiveUserUseCase
) : BaseViewModel() {
    private val _destinationFlow = MutableSharedFlow<Int>()
    val destinationFlow: SharedFlow<Int> = _destinationFlow.asSharedFlow()

    fun checkIfUserIsLoggedIn() {
        viewModelScope(Dispatchers.Main) {
            val user = getActiveUserUseCase()
            if (user != null) {
                _destinationFlow.emit(R.id.action_splashFragment_to_homeFragment)
            } else {
                _destinationFlow.emit(R.id.action_splashFragment_to_startFragment)
            }
        }
    }
}