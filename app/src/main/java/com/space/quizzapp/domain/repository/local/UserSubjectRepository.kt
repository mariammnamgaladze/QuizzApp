package com.space.quizzapp.domain.repository.local

import com.space.quizzapp.domain.model.local.UserSubjectDomainModel

interface UserSubjectRepository {
    suspend fun insertUserSubject(userSubjectDomainModel: UserSubjectDomainModel)

    suspend fun getAllUserSubject(username:String): List<UserSubjectDomainModel>

}