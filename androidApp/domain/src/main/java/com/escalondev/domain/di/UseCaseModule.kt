package com.escalondev.domain.di

import com.escalondev.domain.usecase.home.GetMoviesUseCase
import com.escalondev.domain.usecase.home.GetMoviesUseCaseImpl
import com.escalondev.domain.usecase.profile.GetProfileUseCase
import com.escalondev.domain.usecase.profile.GetProfileUseCaseImpl
import com.escalondev.domain.usecase.watchlist.GetWatchlistMoviesUseCase
import com.escalondev.domain.usecase.watchlist.GetWatchlistMoviesUseCaseImpl
import com.escalondev.domain.usecase.watchlist.GetWatchlistUseCase
import com.escalondev.domain.usecase.watchlist.GetWatchlistUseCaseImpl
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import com.escalondev.movies_app_kmp.core.provider.profile.ProfileUseCaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * The SharedSDK module acts as a bridge to simplify the provision of SDK instances,
 * such as the SharedUseCaseProvider. It integrates seamlessly with Hilt for Dependency Injection (DI),
 * allowing these instances to be easily injected into any class constructor that utilizes Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(moviesUseCaseProvider: MoviesUseCaseProvider): GetMoviesUseCase {
        return GetMoviesUseCaseImpl(moviesUseCaseProvider)
    }

    @Provides
    fun provideGetWatchlistUseCase(moviesUseCaseProvider: MoviesUseCaseProvider): GetWatchlistUseCase {
        return GetWatchlistUseCaseImpl(moviesUseCaseProvider)
    }

    @Provides
    fun provideGetWatchlistMoviesUseCase(moviesUseCaseProvider: MoviesUseCaseProvider): GetWatchlistMoviesUseCase {
        return GetWatchlistMoviesUseCaseImpl(moviesUseCaseProvider)
    }

    @Provides
    fun provideProfileUseCase(profileProvider: ProfileUseCaseProvider): GetProfileUseCase {
        return GetProfileUseCaseImpl(profileProvider)
    }
}
