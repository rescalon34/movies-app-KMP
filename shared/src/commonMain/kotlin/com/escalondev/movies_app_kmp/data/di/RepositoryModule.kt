package com.escalondev.movies_app_kmp.data.di

import com.escalondev.movies_app_kmp.data.repository.MoviesRepositoryImpl
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}
