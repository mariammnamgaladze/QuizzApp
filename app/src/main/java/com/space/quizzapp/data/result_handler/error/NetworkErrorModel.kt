package com.space.quizzapp.data.result_handler.error

data class NetworkErrorModel(val errorMessage: String) : Throwable(errorMessage)