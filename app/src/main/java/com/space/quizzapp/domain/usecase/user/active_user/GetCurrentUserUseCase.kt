package com.space.quizzapp.domain.usecase.user.active_user

import com.space.quizzapp.domain.model.UserDomainModel

interface GetCurrentUserUseCase {
    suspend operator fun invoke(isActive: Boolean): UserDomainModel
}