package com.space.quizzapp.domain.usecase.subject

import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.domain.repository.local.UserSubjectRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase

class GetUserSubjectUseCase(private val userSubjectRepository: UserSubjectRepository) :
    BaseUseCase<String, List<UserSubjectDomainModel>>() {
    override suspend fun invoke(params: String?): List<UserSubjectDomainModel> {
        return userSubjectRepository.getAllUserSubject(params!!)
    }
}