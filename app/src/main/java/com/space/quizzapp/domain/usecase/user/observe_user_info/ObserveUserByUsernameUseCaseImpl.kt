package com.space.quizzapp.domain.usecase.user.observe_user_info

import com.space.quizzapp.domain.model.DomainUserModel
import com.space.quizzapp.domain.repository.local.UserRepository
import kotlinx.coroutines.flow.Flow
class ObserveUserByUsernameUseCaseImpl(private val userRepository: UserRepository) : ObserveUserByUsernameUseCase  {
    override suspend fun execute(username: String): Flow<DomainUserModel> {
        return userRepository.observeUserByUsername(username)
    }
}