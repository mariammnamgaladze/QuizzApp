package com.space.quizzapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points_info")
data class UserSubjectEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val quizDescription: String,
    val quizIcon: String,
    val quizTitle: String,
    val collectedPoints:Int,
    val userName:String
)