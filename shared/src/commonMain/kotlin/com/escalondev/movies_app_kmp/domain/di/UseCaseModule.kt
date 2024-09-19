package com.escalondev.movies_app_kmp.domain.di

import com.escalondev.movies_app_kmp.domain.usecase.GetWatchlistUseCase
import com.escalondev.movies_app_kmp.domain.usecase.GetWatchlistUseCaseImpl
import org.koin.dsl.module

/**
 * Add all useCase instances in this module
 */
val useCaseModule = module {
    single<GetWatchlistUseCase> { GetWatchlistUseCaseImpl(get()) }
}
