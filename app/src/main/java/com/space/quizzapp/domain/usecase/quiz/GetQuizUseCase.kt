package com.space.quizzapp.domain.usecase.quiz

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.QuizItemDomainModel
import com.space.quizzapp.domain.repository.remote.QuizRepository
import com.space.quizzapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
class GetQuizUseCase(private val quizRepository: QuizRepository) : BaseUseCase<Unit, Flow<ResponseHandler<List<QuizItemDomainModel>>>>() {
    override suspend fun invoke(params: Unit?): Flow<ResponseHandler<List<QuizItemDomainModel>>> {
        return quizRepository.getQuizQuestions()
    }
}
