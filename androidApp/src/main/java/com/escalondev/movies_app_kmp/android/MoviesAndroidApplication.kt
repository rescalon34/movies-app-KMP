package com.escalondev.movies_app_kmp.android

import android.app.Application
import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MoviesAndroidApplication : Application() {

    @Inject
    lateinit var sharedCoreManager: SharedCoreManager

    override fun onCreate() {
        super.onCreate()
        sharedCoreManager.init()
    }
}
