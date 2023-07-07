package com.space.quizzapp.data.remote.network

import com.space.quizzapp.data.remote.model.QuizItemDTO
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {
    @GET("8ade4e0b-bee1-4eae-a98b-47edeea68324")
    suspend fun getQuizQuestions(): Response<List<QuizItemDTO>>
}