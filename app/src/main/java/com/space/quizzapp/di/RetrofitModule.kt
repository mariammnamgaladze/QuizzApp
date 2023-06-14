package com.space.quizzapp.di

import com.space.quizzapp.data.remote.network.QuizApiService
import com.space.quizzapp.data.remote.network.QuizApiService.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofitModule = module {
    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(get())
            )
            .build()
    }
    single {
        apiService(get())
    }

}

fun apiService(retrofit: Retrofit): QuizApiService =
    retrofit.create(QuizApiService::class.java)