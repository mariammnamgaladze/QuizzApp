package com.space.quizzapp.domain.usecase.user.user_validation

interface UsernameValidationUseCase {
    suspend fun isUsernameAvailable(username: String): Boolean
}