package com.space.quizzapp.data.repository.remote

import com.space.quizzapp.common.resource.ResponseHandler
import com.space.quizzapp.data.remote.network.QuizApiService
import com.space.quizzapp.data.result_handler.error.NetworkErrorModel
import com.space.quizzapp.data.result_handler.retrofit.apiDataFetcher
import com.space.quizzapp.domain.model.remote.QuizItemDomainModel
import com.space.quizzapp.domain.model.remote.mapper.QuizItemDTODomainMapper
import com.space.quizzapp.domain.repository.remote.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizRepositoryImpl(
    private val apiService: QuizApiService,
    private val quizItemDTODomainMapper: QuizItemDTODomainMapper
) : QuizRepository {

    override suspend fun getQuiz(): Flow<ResponseHandler<List<QuizItemDomainModel>>> {
        return flow {
            emit(ResponseHandler.InProcess())
            try {
                val quizItemDTO = apiDataFetcher {
                    apiService.getQuizQuestions()
                }
                val quizSubjectDomainList = quizItemDTO.map {
                    quizItemDTODomainMapper(it)
                }
                emit(ResponseHandler.Success(quizSubjectDomainList))
            } catch (e: NetworkErrorModel) {
                emit(ResponseHandler.Error(e.errorMessage))
            }
        }
    }
}


