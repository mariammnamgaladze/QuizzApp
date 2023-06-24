package com.space.quizzapp.domain.usecase.subject

import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.domain.repository.local.UserSubjectRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase

class InsertUserSubjectUseCase(private val userSubjectRepository: UserSubjectRepository):BaseUseCase<UserSubjectDomainModel,Unit>() {
    override suspend fun invoke(params: UserSubjectDomainModel?) {
        userSubjectRepository.insertUserSubject(params!!)
    }
}
