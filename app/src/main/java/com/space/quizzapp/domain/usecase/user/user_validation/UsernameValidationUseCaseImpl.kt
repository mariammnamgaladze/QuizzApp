package com.space.quizzapp.domain.usecase.user.user_validation

import com.space.quizzapp.domain.repository.local.UserRepository

class UsernameValidationUseCaseImpl(private val userRepository: UserRepository) :
    UsernameValidationUseCase {
    override suspend fun isUsernameAvailable(username: String): Boolean {
        return userRepository.isUsernameAvailable(username)
    }
}