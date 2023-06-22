package com.space.quizzapp.domain.usecase.user.save_user

import com.space.quizzapp.domain.model.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository

class SaveUserUseCaseImpl(private val userRepository: UserRepository) : SaveUserUseCase {
    override suspend fun invoke(user: UserDomainModel) {
        return userRepository.insertUser(user)
    }
}