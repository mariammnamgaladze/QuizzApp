package com.space.quizzapp.data.result_handler.error

data class NetworkErrorThrowable(val errorMessage: String) : Throwable(errorMessage)