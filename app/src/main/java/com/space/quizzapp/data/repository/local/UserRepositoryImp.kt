package com.space.quizzapp.data.repository.local

import com.space.quizzapp.data.local.dao.UserDao
import com.space.quizzapp.data.local.entity.mapper.UserDomainToEntityMapper
import com.space.quizzapp.domain.model.local.UserDomainModel
import com.space.quizzapp.domain.model.local.mapper.UserEntityToDomainMapper
import com.space.quizzapp.domain.repository.local.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImp(
    private val userDao: UserDao,
    private val userDomainToEntityMapper: UserDomainToEntityMapper,
    private val userEntityToDomainMapper: UserEntityToDomainMapper
) : UserRepository {
    override suspend fun insertUser(userDomainModel: UserDomainModel) {
        if (!isUsernameAvailable(userDomainModel.username)) {
            userDao.insertUser(userDomainToEntityMapper(userDomainModel))
        } else userDao.updateUserActiveStatus(userDomainModel.username, true)
    }

    override suspend fun isUsernameAvailable(username: String): Boolean {
        return userDao.getUser(username) != null
    }

    override suspend fun observeUser(username: String): Flow<UserDomainModel> {
        return userDao.observeUser(username).map {
            userEntityToDomainMapper(it)
        }
    }

    override suspend fun getCurrentUser(isActive: Boolean): UserDomainModel {
        val userEntity = userDao.getCurrentUser(isActive)
        return userEntityToDomainMapper(userEntity)
    }

    override suspend fun updateUserActiveStatus(username: String, isActive: Boolean) {
        userDao.updateUserActiveStatus(username, isActive)
    }

    override suspend fun updateGpa(username: String, gpa: Float) {
        userDao.updateGpa(username, gpa)
    }
}
