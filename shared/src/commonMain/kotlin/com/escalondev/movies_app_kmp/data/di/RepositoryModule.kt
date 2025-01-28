package com.escalondev.movies_app_kmp.data.di

import com.escalondev.movies_app_kmp.data.repository.SharedMoviesRepositoryImpl
import com.escalondev.movies_app_kmp.data.repository.SharedProfileRepositoryImpl
import com.escalondev.movies_app_kmp.domain.repository.SharedMoviesRepository
import com.escalondev.movies_app_kmp.domain.repository.SharedProfileRepository
import org.koin.dsl.module

/**
 * This module host all repository instances within the Shared Module.
 */
internal val repositoryModule = module {
    single<SharedMoviesRepository> { SharedMoviesRepositoryImpl(get()) }
    single<SharedProfileRepository> { SharedProfileRepositoryImpl(get()) }
}
