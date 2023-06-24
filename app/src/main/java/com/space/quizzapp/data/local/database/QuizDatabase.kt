package com.space.quizzapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quizzapp.data.local.dao.UserDao
import com.space.quizzapp.data.local.dao.UserSubjectDao
import com.space.quizzapp.data.local.database.QuizDatabase.Companion.DB_VERSION
import com.space.quizzapp.data.local.entity.UserEntityModel
import com.space.quizzapp.data.local.entity.UserSubjectEntityModel

@Database(
    entities = [UserEntityModel::class,UserSubjectEntityModel::class],
    version = DB_VERSION, exportSchema = true
)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userSubjectDao(): UserSubjectDao

    companion object {
        const val DB_VERSION = 6
    }
}