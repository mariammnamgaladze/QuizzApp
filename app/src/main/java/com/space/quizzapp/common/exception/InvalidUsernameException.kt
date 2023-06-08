package com.space.quizzapp.common.exception

data class InvalidUsernameException(override val message: String) : Exception(message)
