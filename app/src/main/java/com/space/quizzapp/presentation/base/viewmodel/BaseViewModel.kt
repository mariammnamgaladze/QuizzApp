package com.space.quizzapp.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.common.navigation.NavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableSharedFlow<NavigationCommand>()
    val navigation: SharedFlow<NavigationCommand> get() = _navigation

    fun navigate(navDirections: NavDirections) {
        viewModelScope{
            _navigation.emit(NavigationCommand.ToDirection(navDirections))
        }
    }

    fun navigateBack() {
        viewModelScope{
            _navigation.emit(NavigationCommand.Back)
        }
    }

}
