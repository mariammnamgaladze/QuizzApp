package com.space.quizzapp.domain.usecase.user.save_user

import com.space.quizzapp.domain.model.DomainUserModel
import com.space.quizzapp.domain.repository.local.UserRepository

class SaveUserInfoUseCaseImpl(private val userRepository: UserRepository) : SaveUserInfoUseCase {
    override suspend fun invoke(user: DomainUserModel) {
        return userRepository.saveAllUserInfo(user)
    }
}