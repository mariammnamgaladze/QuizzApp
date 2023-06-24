package com.space.quizzapp.data.local.dao

import androidx.room.*
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel

@Dao
interface UserSubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSubjects(userSubject: UserSubjectEntityModel)

    @Query("SELECT * FROM points_info WHERE userName = :userName AND quizTitle = :quizTitle")
    suspend fun getUserSubjectIfExist(userName: String, quizTitle: String): UserSubjectEntityModel?

    @Query("SELECT * FROM points_info WHERE userName = :userName")
    suspend fun getAllUserSubject(userName: String):List<UserSubjectEntityModel>

    @Delete
    suspend fun deleteUserSubject(userSubject: UserSubjectEntityModel)
}