package com.space.quizzapp

import android.app.Application
import com.space.quizzapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class QuizzApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuizzApp)
            modules(
                UseCaseModule, databaseModule, repositoryModule, viewModelModule, retrofitModule
            )
        }
    }
}