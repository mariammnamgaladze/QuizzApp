package com.space.quizzapp.domain.usecase.user.observe_user

import com.space.quizzapp.domain.model.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository
import kotlinx.coroutines.flow.Flow
class ObserveUserUseCaseImpl(private val userRepository: UserRepository) : ObserveUserUseCase  {
    override suspend fun execute(username: String): Flow<UserDomainModel> {
        return userRepository.observeUser(username)
    }
}