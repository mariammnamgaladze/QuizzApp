package com.space.quizzapp.common.resource

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: Int) : Result<Nothing>()
}