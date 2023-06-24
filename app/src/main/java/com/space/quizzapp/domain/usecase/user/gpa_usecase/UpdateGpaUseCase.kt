package com.space.quizzapp.domain.usecase.user.gpa_usecase

import com.space.quizzapp.domain.repository.local.UserRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase
import com.space.quizzapp.domain.usecase.subject.GetUserSubjectUseCase

class UpdateGpaUseCase(
    private val userRepository: UserRepository,
    private val getUserSubjectUseCase: GetUserSubjectUseCase
) :
    BaseUseCase<String, Unit>() {
    override suspend operator fun invoke(params: String?) {
        val subjectPointPercent = mutableListOf<Float>(
        )
        getUserSubjectUseCase.invoke(params).forEach {
            subjectPointPercent.add(it.collectedPoints.toFloat() / it.questionsCount)
        }
        val gpa = subjectPointPercent.average() * 4
        userRepository.updateGpa(params!!, gpa.toFloat())
    }
}