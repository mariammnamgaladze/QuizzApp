package com.space.quizzapp.domain.usecase.subject

import com.space.quizzapp.domain.model.local.UserSubjectDomainModel
import com.space.quizzapp.domain.repository.local.UserSubjectRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase
import com.space.quizzapp.domain.usecase.user.gpa_usecase.UpdateGpaUseCase

class InsertUserSubjectUseCase(
    private val userSubjectRepository: UserSubjectRepository,
    private val updateGpaUseCase: UpdateGpaUseCase
) : BaseUseCase<UserSubjectDomainModel, Unit>() {
    override suspend fun invoke(params: UserSubjectDomainModel?) {
        userSubjectRepository.insertUserSubject(params!!)
        updateGpaUseCase(params.userName)
    }
}
