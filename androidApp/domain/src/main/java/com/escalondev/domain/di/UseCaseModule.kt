package com.escalondev.domain.di

import com.escalondev.domain.usecase.home.GetMoviesUseCase
import com.escalondev.domain.usecase.home.GetMoviesUseCaseImpl
import com.escalondev.domain.usecase.watchlist.GetWatchlistMoviesUseCase
import com.escalondev.domain.usecase.watchlist.GetWatchlistMoviesUseCaseImpl
import com.escalondev.domain.usecase.watchlist.GetWatchlistUseCase
import com.escalondev.domain.usecase.watchlist.GetWatchlistUseCaseImpl
import com.escalondev.movies_app_kmp.core.manager.SharedCoreManager
import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProvider
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
object UseCaseModule {

    @Provides
    fun provideUseCaseProvider(): SharedUseCaseProvider {
        return SharedCoreManager.getInstance().useCaseProvider
    }

    @Provides
    fun provideGetMoviesUseCase(): GetMoviesUseCase {
        return GetMoviesUseCaseImpl(provideUseCaseProvider())
    }

    @Provides
    fun provideGetWatchlistUseCase(): GetWatchlistUseCase {
        return GetWatchlistUseCaseImpl(provideUseCaseProvider())
    }

    @Provides
    fun provideGetWatchlistMoviesUseCase(): GetWatchlistMoviesUseCase {
        return GetWatchlistMoviesUseCaseImpl(provideUseCaseProvider())
    }
}
