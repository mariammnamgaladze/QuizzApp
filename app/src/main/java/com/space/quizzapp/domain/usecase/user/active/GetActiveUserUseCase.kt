package com.space.quizzapp.domain.usecase.user.active

import com.space.quizzapp.domain.model.local.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase

class GetActiveUserUseCase(private val userRepository: UserRepository) :
    BaseUseCase<Unit, UserDomainModel?>() {
    override suspend fun invoke(params: Unit?): UserDomainModel? {
        return userRepository.getActiveUser()
    }
}