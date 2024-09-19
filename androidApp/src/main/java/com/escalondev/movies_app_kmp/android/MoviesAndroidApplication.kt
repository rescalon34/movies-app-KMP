package com.escalondev.movies_app_kmp.android

import android.app.Application
import com.escalondev.movies_app_kmp.data.di.sharedCoreModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class MoviesAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoinModule()
    }

    private fun setupKoinModule() {
        startKoin {
            androidContext(this@MoviesAndroidApplication)
            modules(sharedCoreModule)
        }
    }
}
