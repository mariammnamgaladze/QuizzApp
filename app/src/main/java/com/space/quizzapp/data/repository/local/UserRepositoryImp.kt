package com.space.quizzapp.data.repository.local

import com.space.quizzapp.data.local.dao.UserDao
import com.space.quizzapp.data.mapper.toDomainModel
import com.space.quizzapp.data.mapper.toEntity
import com.space.quizzapp.domain.model.UserDomainModel
import com.space.quizzapp.domain.repository.local.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImp(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(userDomainModel: UserDomainModel) {
        if (!isUsernameAvailable(userDomainModel.username)) {
            userDao.insertUser(userDomainModel.toEntity())
        } else userDao.updateUserActiveStatus(userDomainModel.username, true)
    }

    override suspend fun isUsernameAvailable(username: String): Boolean {
        return userDao.getUser(username) != null
    }

    override suspend fun observeUser(username: String): Flow<UserDomainModel> {
        return userDao.observeUser(username).map {
            it.toDomainModel()
        }
    }

    override suspend fun getCurrentUser(isActive: Boolean): UserDomainModel {
        val userEntity = userDao.getCurrentUser(isActive)
        return userEntity.toDomainModel()
    }

    override suspend fun updateUserActiveStatus(username: String, isActive: Boolean) {
        userDao.updateUserActiveStatus(username, isActive)
    }
}