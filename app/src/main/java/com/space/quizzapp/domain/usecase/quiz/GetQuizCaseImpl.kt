package com.space.quizzapp.domain.usecase.quiz

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.QuizItemDomainModel
import com.space.quizzapp.domain.repository.remote.QuizRepository
import kotlinx.coroutines.flow.Flow

class GetQuizUseCaseImpl(private val quizRepository: QuizRepository) : GetQuizUseCase {
    override suspend fun execute(): Flow<ResponseHandler<List<QuizItemDomainModel>>> {
        return quizRepository.getQuizQuestions()
    }
}
