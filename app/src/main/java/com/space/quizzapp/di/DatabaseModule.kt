package com.space.quizzapp.di

import android.content.Context
import androidx.room.Room
import com.space.quizzapp.data.local.database.UserDatabase
import org.koin.dsl.module

private fun provideMessagesDatabase(context: Context): UserDatabase {
    return Room.databaseBuilder(
        context,
       UserDatabase::class.java,
        "messages"
    )
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideDao(database: UserDatabase) = database.userDao()
val databaseModule = module {
    single { provideMessagesDatabase(get()) }
    single { provideDao(get()) }
}