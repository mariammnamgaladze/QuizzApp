package com.space.quizzapp.domain.usecase.user.update_user_status

interface UpdateUserActiveStatusUseCase {
    suspend fun updateUserActiveStatus(username: String, isActive: Boolean)
}