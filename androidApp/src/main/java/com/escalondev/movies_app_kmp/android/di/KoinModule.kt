package com.escalondev.movies_app_kmp.android.di

import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import com.escalondev.movies_app_kmp.core.util.getKoinGlobalContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This Koin Module will allows us to inject SharedCoreManager as a provider
 * by communicating with the `GlobalContext` from the shared Koin module.
 *
 * It works as a bridge, to make the SharedCoreManager a instance that can easily
 * be injected in any constructor class that uses Hilt as DI.
 */
@Module
@InstallIn(SingletonComponent::class)
object KoinModule {

    @Provides
    fun provideSharedCoreManager(): SharedCoreManager {
        return getKoinGlobalContext().get()
    }
}
