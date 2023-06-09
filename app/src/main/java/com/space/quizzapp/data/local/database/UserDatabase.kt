package com.space.quizzapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quizzapp.data.local.dao.UserDao
import com.space.quizzapp.data.local.database.UserDatabase.Companion.DB_VERSION
import com.space.quizzapp.data.local.entity.UserEntityModel

@Database(
    entities = [UserEntityModel::class],
    version = DB_VERSION, exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DB_VERSION = 6
    }
}