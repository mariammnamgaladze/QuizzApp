package com.space.quizzapp.domain.usecase.user.update_user_status

import com.space.quizzapp.domain.repository.local.UserRepository

class UpdateUserActiveStatusUseCaseImpl(private val userRepository: UserRepository) :
    UpdateUserActiveStatusUseCase {

    override suspend fun updateUserActiveStatus(username: String, isActive: Boolean) {
        userRepository.updateUserActiveStatus(username, isActive)
    }
}