package com.space.quizzapp.domain.usecase.user.user_validation

import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase

class UsernameValidationUseCase(private val userRepository: UserRepository) :
    BaseUseCase<String, Boolean>() {

    override suspend operator fun invoke(params: String?): Boolean {
        return userRepository.isUsernameAvailable(params!!)
    }
}