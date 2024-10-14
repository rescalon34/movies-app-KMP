package com.escalondev.movies_app_kmp.domain.di

import com.escalondev.movies_app_kmp.domain.usecase.home.GetMoviesUseCase
import com.escalondev.movies_app_kmp.domain.usecase.home.GetMoviesUseCaseImpl
import com.escalondev.movies_app_kmp.domain.usecase.watchlist.GetWatchlistMoviesUseCase
import com.escalondev.movies_app_kmp.domain.usecase.watchlist.GetWatchlistMoviesUseCaseImpl
import com.escalondev.movies_app_kmp.domain.usecase.watchlist.GetWatchlistUseCase
import com.escalondev.movies_app_kmp.domain.usecase.watchlist.GetWatchlistUseCaseImpl
import org.koin.dsl.module

/**
 * Add all useCase instances in this module
 */
internal val useCaseModule = module {
    single<GetWatchlistUseCase> { GetWatchlistUseCaseImpl(get()) }
    single<GetWatchlistMoviesUseCase> { GetWatchlistMoviesUseCaseImpl(get()) }
    single<GetMoviesUseCase> { GetMoviesUseCaseImpl(get()) }
}
