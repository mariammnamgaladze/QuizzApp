package com.space.quizzapp.domain.usecase.user.active_user

import com.space.quizzapp.domain.model.DomainUserModel
import com.space.quizzapp.domain.repository.local.UserRepository

class GetCurrentUserInfoUseCaseImpl(private val userRepository: UserRepository) : GetCurrentUserInfoUseCase {
    override suspend operator fun invoke(isActive: Boolean): DomainUserModel {
        return userRepository.getCurrentUser(isActive)
    }

}