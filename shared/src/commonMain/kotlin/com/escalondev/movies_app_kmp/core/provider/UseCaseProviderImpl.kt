@file:Suppress("DEPRECATION")

package com.escalondev.movies_app_kmp.core.provider

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.usecase.GetWatchlistUseCase
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * This class is intended to store all useCases instances for better organization.
 */
internal class UseCaseProviderImpl : KoinComponent, UseCaseProvider {
    private val getWatchlistUseCase: GetWatchlistUseCase by inject<GetWatchlistUseCase>()

    @NativeCoroutines
    override fun getWatchlist(): Flow<List<Movie>> = getWatchlistUseCase()
}
