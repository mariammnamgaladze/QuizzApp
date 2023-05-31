package com.space.quizzapp

import android.app.Application
import com.space.quizzapp.di.UseCaseModule
import com.space.quizzapp.di.databaseModule
import com.space.quizzapp.di.repositoryModule
import com.space.quizzapp.di.viewModelModule
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
                UseCaseModule,databaseModule,repositoryModule,viewModelModule
            )
        }
    }
}