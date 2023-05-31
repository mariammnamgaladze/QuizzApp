package com.space.quizzapp.domain.usecase.user.save_user

import com.space.quizzapp.domain.model.DomainUserModel

interface SaveUserInfoUseCase {
    suspend fun invoke(user: DomainUserModel)
}