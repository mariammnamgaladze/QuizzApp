package com.space.quizzapp.data.repository.local

import com.space.quizzapp.data.local.dao.UserSubjectDao
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel
import com.space.quizzapp.data.local.entity.mapper.UserSubjectDomainToEntityMapper
import com.space.quizzapp.domain.model.local.UserDomainModel
import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.domain.model.local.mapper.UserSubjectEntityToDomainMapper
import com.space.quizzapp.domain.repository.local.UserSubjectRepository

class UserSubjectRepositoryImpl(
    private val userSubjectDao: UserSubjectDao,
    private val domainToEntity: UserSubjectDomainToEntityMapper,
    private val entityToDomain: UserSubjectEntityToDomainMapper,

    ) : UserSubjectRepository {
    override suspend fun insertUserSubject(userSubjectDomainModel: UserSubjectDomainModel) {
        val existedUserSubjects = userSubjectDao.getUserSubjectIfExist(
            userSubjectDomainModel.userName,
            userSubjectDomainModel.quizTitle
        )
        existedUserSubjects?.let {
            if (it.collectedPoints < userSubjectDomainModel.collectedPoints) {
                userSubjectDao.deleteUserSubject(it)
                userSubjectDao.insertUserSubjects(domainToEntity(userSubjectDomainModel))
            }
            return
        }
        userSubjectDao.insertUserSubjects(domainToEntity(userSubjectDomainModel))
    }

    override suspend fun getAllUserSubject(username: String): List<UserSubjectDomainModel> {
        return userSubjectDao.getAllUserSubject(username).map {
            entityToDomain(it)
        }
    }
}
