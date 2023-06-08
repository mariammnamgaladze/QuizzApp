package com.space.quizzapp.domain.model

data class UserDomainModel(
    val id: Int? = null,
    var username: String,
    var isActive: Boolean
)