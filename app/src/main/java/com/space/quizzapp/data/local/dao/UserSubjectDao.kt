package com.space.quizzapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.space.quizzapp.data.local.entity.UserEntityModel
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel

@Dao
interface UserSubjectDao {
    @Insert
    suspend fun insertUserSubjects(userSubject: UserSubjectEntityModel)

    @Query("SELECT * FROM points_info WHERE userName = :userName AND quizTitle = :quizTitle")
    suspend fun getUserSubjectIfExist(userName: String, quizTitle: String): UserSubjectEntityModel?

    @Query("SELECT * FROM points_info WHERE userName = :userName")
    suspend fun getAllUserSubject(userName: String):List<UserSubjectEntityModel>

    @Delete
    suspend fun deleteUserSubject(userSubject: UserSubjectEntityModel)
}