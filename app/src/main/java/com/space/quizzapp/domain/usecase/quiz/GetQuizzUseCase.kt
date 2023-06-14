package com.space.quizzapp.domain.usecase.quiz

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.QuizItemDomainModel
import kotlinx.coroutines.flow.Flow

interface GetQuizUseCase {
    suspend fun execute(): Flow<ResponseHandler<List<QuizItemDomainModel>>>
}
