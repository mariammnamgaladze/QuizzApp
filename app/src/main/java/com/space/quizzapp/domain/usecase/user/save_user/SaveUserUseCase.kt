package com.space.quizzapp.domain.usecase.user.save_user

import com.space.quizzapp.domain.model.local.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase

class SaveUserUseCase(private val userRepository: UserRepository) :
    BaseUseCase<UserDomainModel, Unit>() {

    override suspend operator fun invoke(params: UserDomainModel?) {
        userRepository.insertUser(params!!)
    }
}
