package com.space.quizzapp.data.repository.remote

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.domain.model.remote.mapper.QuizItemDTODomainMapper
import com.space.quizzapp.data.remote.network.QuizApiService
import com.space.quizzapp.domain.model.remote.QuizItemDomainModel
import com.space.quizzapp.domain.repository.remote.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizRepositoryImpl(
    private val apiService: QuizApiService,
    private val quizItemDTODomainMapper: QuizItemDTODomainMapper,
) : QuizRepository {

    override suspend fun getQuiz(): Flow<ResponseHandler<List<QuizItemDomainModel>>> {
        return flow {
            emit(ResponseHandler.InProcess())
            val response = apiService.getQuizQuestions()
            if (response.isSuccessful && response.body() != null) {
                val quizItemDTO = response.body()!!
                val quizSubjectDomainList = quizItemDTO.map {
                    quizItemDTODomainMapper(it)
                }
                emit(ResponseHandler.Success(quizSubjectDomainList))
            } else {
                emit(ResponseHandler.Error(""))
            }
        }
    }
}


