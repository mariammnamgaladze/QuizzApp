package com.space.quizzapp.domain.usecase.user.active_user

import com.space.quizzapp.domain.model.DomainUserModel

interface GetCurrentUserInfoUseCase {
    suspend operator fun invoke(isActive: Boolean): DomainUserModel
}