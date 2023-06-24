package com.space.quizzapp.domain.model.local

data class UserDomainModel(
    val id: Int? = null,
    var username: String,
    var isActive: Boolean
)