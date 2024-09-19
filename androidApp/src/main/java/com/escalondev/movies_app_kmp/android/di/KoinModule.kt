package com.escalondev.movies_app_kmp.android.di

import com.escalondev.movies_app_kmp.domain.usecase.GetWatchlistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.core.context.GlobalContext

@Module
@InstallIn(SingletonComponent::class)
object KoinModule {

    @Provides
    fun provideGetWatchlistUseCase(): GetWatchlistUseCase {
        return GlobalContext.get().get()
    }
}
