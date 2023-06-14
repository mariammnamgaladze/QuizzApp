package com.space.quizzapp.data.repository.remote

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.mapper.QuizItemMapper
import com.space.quizzapp.data.remote.network.QuizApiService
import com.space.quizzapp.domain.model.QuizItemDomainModel
import com.space.quizzapp.domain.repository.remote.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizRepositoryImpl(apiService: QuizApiService) : QuizRepository {
    private val quizApiService = apiService

    override suspend fun getQuizQuestions(): Flow<ResponseHandler<List<QuizItemDomainModel>>> {
        return flow {
            emit(ResponseHandler.InProcess())
            val response = quizApiService.getQuizQuestions()
            if (response.isSuccessful && response.body() != null) {
                val quizItemDTO = response.body()!!
                val quizItemMapper = QuizItemMapper()
                val quizQuestionDomainList = quizItemDTO.map { quizItemMapper(it) }
                emit(ResponseHandler.Success(quizQuestionDomainList))
            } else {
                emit(ResponseHandler.Error("Error"))
            }
        }
    }
}
