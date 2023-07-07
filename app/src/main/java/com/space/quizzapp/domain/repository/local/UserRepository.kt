package com.space.quizzapp.domain.repository.local

import com.space.quizzapp.domain.model.local.UserDomainModel


interface UserRepository {
    suspend fun insertUser(userDomainModel: UserDomainModel)
    suspend fun isUsernameAvailable(username: String): Boolean
    suspend fun getCurrentUser(isActive: Boolean): UserDomainModel?
    suspend fun updateUserActiveStatus(username: String, isActive: Boolean)
    suspend fun updateGpa(username: String, gpa: Float)
    suspend fun getActiveUser(): UserDomainModel?
}