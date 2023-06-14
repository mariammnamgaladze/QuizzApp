package com.space.quizzapp.domain.repository.remote

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.QuizItemDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getQuizQuestions(): Flow<ResponseHandler<List<QuizItemDomainModel>>>
}