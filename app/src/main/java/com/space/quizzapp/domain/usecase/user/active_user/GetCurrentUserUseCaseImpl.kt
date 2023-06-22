package com.space.quizzapp.domain.usecase.user.active_user

import com.space.quizzapp.domain.model.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository

class GetCurrentUserUseCaseImpl(private val userRepository: UserRepository) : GetCurrentUserUseCase {
    override suspend operator fun invoke(isActive: Boolean): UserDomainModel {
        return userRepository.getCurrentUser(isActive)
    }

}