package com.space.quizzapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserEntityModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var username: String,
    var isActive: Boolean,
    var gpa: Float
)
