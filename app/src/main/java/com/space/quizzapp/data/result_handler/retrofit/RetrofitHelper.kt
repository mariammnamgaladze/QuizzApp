package com.space.quizzapp.data.result_handler.retrofit

import com.space.quizzapp.data.result_handler.error.NetworkErrorThrowable
import retrofit2.Response

inline fun <DTO> apiDataFetcher(
    apiResponse: () -> Response<DTO>,
): DTO {
    return try {
        val response = apiResponse.invoke()
        if (response.isSuccessful) {
            response.body()!!
        } else {
            throw NetworkErrorThrowable(errorMessage = response.message())
        }
    } catch (e: Exception) {
        throw NetworkErrorThrowable(errorMessage = e.message!!)
    }
}