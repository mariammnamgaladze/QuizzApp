package com.space.quizzapp.domain.usecase.user.active_user

import com.space.quizzapp.domain.model.local.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase

class GetCurrentUserUseCase(private val userRepository: UserRepository) :
    BaseUseCase<Boolean, UserDomainModel>() {

    override suspend operator fun invoke(params: Boolean?): UserDomainModel {
        return userRepository.getCurrentUser(params!!)
    }
}