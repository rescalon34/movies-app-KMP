package com.escalondev.movies_app_kmp.data.di

import com.escalondev.movies_app_kmp.data.repository.MoviesRepositoryImpl
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import org.koin.dsl.module

/**
 * This module host all repository instances within the Shared Module.
 */
internal val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}
