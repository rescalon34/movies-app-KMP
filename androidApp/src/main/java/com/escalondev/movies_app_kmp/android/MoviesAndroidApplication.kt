package com.escalondev.movies_app_kmp.android

import android.app.Application
import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedCoreManager.init()
    }
}
