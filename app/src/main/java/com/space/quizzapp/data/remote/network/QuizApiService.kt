package com.space.quizzapp.data.remote.network

import com.space.quizzapp.data.remote.model.QuizItemDTO
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {
    @GET("e7d3bc76-6574-4afd-a294-729fe9d56ed5")
    suspend fun getQuizQuestions(): Response<List<QuizItemDTO>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}