package com.space.quizzapp.presentation.detail.viewmodel

import androidx.navigation.NavDirections
import com.space.quizzapp.common.extensions.viewModelScope
import com.space.quizzapp.domain.usecase.subject.GetUserSubjectUseCase
import com.space.quizzapp.domain.usecase.user.active_user.GetCurrentUserUseCase
import com.space.quizzapp.domain.usecase.user.update_user_status.UpdateUserActiveStatusUseCase
import com.space.quizzapp.presentation.authentication.fragment.AuthenticationFragmentDirections
import com.space.quizzapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizzapp.presentation.detail.fragment.DetailsFragmentDirections
import com.space.quizzapp.presentation.model.local.UserSubjectUIModel
import com.space.quizzapp.presentation.model.local.mapper.UserSubjectDomainToUIMapper
import kotlinx.coroutines.flow.*

class DetailsViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateUserActiveStatusUseCase: UpdateUserActiveStatusUseCase,
    private val getUserSubjectUseCase: GetUserSubjectUseCase,
    private val userSubjectDomainToUIMapper: UserSubjectDomainToUIMapper
) : BaseViewModel() {

    private val _subjectsItem = MutableStateFlow<List<UserSubjectUIModel>>(emptyList())
    val subjectsItem: StateFlow<List<UserSubjectUIModel>> = _subjectsItem

    private var activeUsername: String = ""

    fun getUserSubject() {
        viewModelScope {
            val currentUser = getCurrentUserUseCase(true)
            val userSubjects = getUserSubjectUseCase(currentUser.username)
            val userSubjectUIModels = userSubjects.map { userSubjectDomainToUIMapper(it) }
            _subjectsItem.value = userSubjectUIModels
        }
    }

    fun updateActiveStatus(isActive: Boolean) {
        viewModelScope {
            val activeUser = getCurrentUserUseCase.invoke(true)
            activeUsername = activeUser.username
        }
        viewModelScope {
            updateUserActiveStatusUseCase.invoke(activeUsername to isActive)
        }
    }

    fun navigateTo(destination: NavDirections) {
        navigate(destination)
    }

}