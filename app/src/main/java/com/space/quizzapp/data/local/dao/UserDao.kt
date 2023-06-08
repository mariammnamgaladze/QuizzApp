package com.space.quizzapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizzapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_info WHERE username = :username")
    fun observeUser(username: String): Flow<UserEntity>

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_info WHERE username = :username")
    suspend fun getUser(username: String): UserEntity

    @Query("SELECT * FROM user_info WHERE isActive = :isActive")
    suspend fun getCurrentUser(isActive: Boolean): UserEntity

    @Query("UPDATE user_info SET isActive = :isActive WHERE username = :username")
    suspend fun updateUserActiveStatus(username: String, isActive: Boolean)
}