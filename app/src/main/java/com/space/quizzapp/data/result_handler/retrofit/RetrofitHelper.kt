package com.space.quizzapp.data.result_handler.retrofit

import com.space.quizzapp.data.result_handler.error.NetworkErrorModel
import retrofit2.Response

inline fun <DTO> apiDataFetcher(
    apiResponse: () -> Response<DTO>,
): DTO {
    return try {
        val response = apiResponse.invoke()
        if (response.isSuccessful) {
            response.body()!!
        } else {
            throw NetworkErrorModel(errorMessage = response.message())
        }
    } catch (e: Exception) {
        throw NetworkErrorModel(errorMessage = e.message!!)
    }
}