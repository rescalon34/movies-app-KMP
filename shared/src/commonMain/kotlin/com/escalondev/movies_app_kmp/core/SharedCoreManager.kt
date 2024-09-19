package com.escalondev.movies_app_kmp.core

import com.escalondev.movies_app_kmp.data.di.sharedCoreModule
import com.escalondev.movies_app_kmp.domain.usecase.GetWatchlistUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class SharedCoreManager : KoinComponent {

    fun initKoinModule() {
        startKoin {
            modules(sharedCoreModule)
        }
    }

    val getWatchlistUseCase: GetWatchlistUseCase by inject<GetWatchlistUseCase>()
}
