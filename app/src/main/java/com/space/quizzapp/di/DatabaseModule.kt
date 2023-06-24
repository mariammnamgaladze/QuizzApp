package com.space.quizzapp.di

import android.content.Context
import androidx.room.Room
import com.space.quizzapp.data.local.database.QuizDatabase
import org.koin.dsl.module

private fun provideQuizDatabase(context: Context): QuizDatabase {
    return Room.databaseBuilder(
        context,
        QuizDatabase::class.java,
        "quiz"
    )
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideUserDao(database: QuizDatabase) = database.userDao()
private fun provideUserSubjectDao(database: QuizDatabase) = database.userSubjectDao()
val databaseModule = module {
    single { provideQuizDatabase(get()) }
    single { provideUserDao(get()) }
    single { provideUserSubjectDao(get())
    }
}