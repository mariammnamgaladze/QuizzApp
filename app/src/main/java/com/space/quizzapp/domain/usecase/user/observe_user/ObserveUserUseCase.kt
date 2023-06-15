package com.space.quizzapp.domain.usecase.user.observe_user

import com.space.quizzapp.domain.model.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class ObserveUserUseCase(private val userRepository: UserRepository) :
    BaseUseCase<String, Flow<UserDomainModel>>() {

    override suspend operator fun invoke(params: String?): Flow<UserDomainModel> {
        return userRepository.observeUser(params!!)
    }
}
