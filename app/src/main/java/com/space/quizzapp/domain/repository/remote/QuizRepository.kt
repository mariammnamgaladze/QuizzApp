package com.space.quizzapp.domain.repository.remote

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.remote.QuizItemDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getQuiz(): Flow<ResponseHandler<List<QuizItemDomainModel>>>
}