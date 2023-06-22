package com.space.quizzapp.domain.usecase.user.save_user

import com.space.quizzapp.domain.model.UserDomainModel

interface SaveUserUseCase {
    suspend fun invoke(user: UserDomainModel)
}