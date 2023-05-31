package com.space.quizzapp.domain.repository.local

import com.space.quizzapp.domain.model.DomainUserModel
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun saveAllUserInfo(domainUserModel: DomainUserModel)
    suspend fun isUsernameAvailable(username: String): Boolean
    suspend fun observeUserByUsername(username: String): Flow<DomainUserModel>
    suspend fun getCurrentUser(isActive: Boolean): DomainUserModel
    suspend fun updateUserActiveStatus(username: String, isActive: Boolean)

}