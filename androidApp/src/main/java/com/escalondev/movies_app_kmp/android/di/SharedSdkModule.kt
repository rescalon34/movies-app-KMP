package com.escalondev.movies_app_kmp.android.di

import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import com.escalondev.movies_app_kmp.core.util.getKoinGlobalContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * The SharedSdk module acts as a bridge to simplify the provision of SDK instances,
 * such as the SharedCoreManager. It integrates seamlessly with Hilt for Dependency Injection (DI),
 * allowing these instances to be easily injected into any class constructor that utilizes Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object SharedSdkModule {

    @Provides
    fun provideSharedCoreManager(): SharedCoreManager {
        return getKoinGlobalContext().get()
    }
}
