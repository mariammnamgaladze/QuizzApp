package com.space.quizzapp.domain.repository.local

import com.space.quizzapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun insertUser(userDomainModel: UserDomainModel)
    suspend fun isUsernameAvailable(username: String): Boolean
    suspend fun observeUser(username: String): Flow<UserDomainModel>
    suspend fun getCurrentUser(isActive: Boolean): UserDomainModel
    suspend fun updateUserActiveStatus(username: String, isActive: Boolean)

}