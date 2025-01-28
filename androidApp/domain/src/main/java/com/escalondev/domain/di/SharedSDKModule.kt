package com.escalondev.domain.di

import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import com.escalondev.movies_app_kmp.core.provider.profile.ProfileUseCaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * The SharedSDK module acts as a bridge to simplify the provision of SDK instances,
 * such as the SharedCoreManager. It integrates seamlessly with Hilt for Dependency Injection (DI),
 * allowing these instances to be easily injected into any class constructor that injects an object
 * using Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object SharedSDKModule {

    @Provides
    fun provideSharedCoreManager(): SharedCoreManager {
        return SharedCoreManager.getInstance()
    }

    @Provides
    fun provideMoviesUseCaseProvider(sharedCoreManager: SharedCoreManager): MoviesUseCaseProvider {
        return sharedCoreManager.createMoviesUseCaseProvider()
    }

    @Provides
    fun provideProfileUseCaseProvider(sharedCoreManager: SharedCoreManager): ProfileUseCaseProvider {
        return sharedCoreManager.createProfileUseCaseProvider()
    }
}
