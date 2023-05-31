package com.space.quizzapp.data.repository.local

import com.space.quizzapp.data.local.dao.UserDao
import com.space.quizzapp.data.mapper.toDomainModel
import com.space.quizzapp.data.mapper.toEntity
import com.space.quizzapp.domain.model.DomainUserModel
import com.space.quizzapp.domain.repository.local.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImp(private val userDao: UserDao) : UserRepository {
    override suspend fun saveAllUserInfo(domainUserModel: DomainUserModel) {
        userDao.saveAllUserInfo(domainUserModel.toEntity())
    }

    override suspend fun isUsernameAvailable(username: String): Boolean {
        return userDao.getUserByUsername(username) == null
    }

    override suspend fun observeUserByUsername(username: String): Flow<DomainUserModel> {
        return userDao.observeUserByUsername(username).map {
            it.toDomainModel()
        }
    }

    override suspend fun getCurrentUser(isActive: Boolean): DomainUserModel {
        val userEntity = userDao.getCurrentUser(isActive)
        return userEntity.toDomainModel()
    }

    override suspend fun updateUserActiveStatus(username: String, isActive: Boolean) {
        userDao.updateUserActiveStatus(username, isActive)
    }
}