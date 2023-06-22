package com.space.quizzapp.domain.usecase.user.observe_user

import com.space.quizzapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface ObserveUserUseCase {
    suspend fun execute(username: String): Flow<UserDomainModel>
}