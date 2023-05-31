package com.space.quizzapp.domain.usecase.user.observe_user_info

import com.space.quizzapp.domain.model.DomainUserModel
import kotlinx.coroutines.flow.Flow

interface ObserveUserByUsernameUseCase {
    suspend fun execute(username: String): Flow<DomainUserModel>
}