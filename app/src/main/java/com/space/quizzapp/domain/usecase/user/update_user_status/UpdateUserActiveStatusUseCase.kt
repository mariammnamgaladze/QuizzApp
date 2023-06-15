package com.space.quizzapp.domain.usecase.user.update_user_status

import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase

class UpdateUserActiveStatusUseCase(private val userRepository: UserRepository) :
    BaseUseCase<Pair<String, Boolean>, Unit>() {

    override suspend operator fun invoke(params: Pair<String, Boolean>?) {
        val (username, isActive) = params ?: throw IllegalArgumentException("Username and isActive pair must not be null")
        userRepository.updateUserActiveStatus(username, isActive)
    }
}