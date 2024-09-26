@file:Suppress("DEPRECATION")

package com.escalondev.movies_app_kmp.core.provider

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.usecase.watchlist.GetWatchlistMoviesUseCase
import com.escalondev.movies_app_kmp.domain.usecase.watchlist.GetWatchlistUseCase
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * This class is intended to store all internal useCases instances for better organization.
 *
 * The public functions will be used by the consumers (Android, iOS) and the useCase will be limited
 * to internal uses within the SDK.
 */
internal class UseCaseProviderImpl : KoinComponent, UseCaseProvider {

    // UseCases instances
    private val getWatchlistUseCase: GetWatchlistUseCase by inject<GetWatchlistUseCase>()
    private val getWatchlistMoviesUseCase: GetWatchlistMoviesUseCase by inject<GetWatchlistMoviesUseCase>()

    // Exposed functions for the consumers.
    override fun getWatchlist(): Flow<List<Movie>> = getWatchlistUseCase()

    override suspend fun getWatchlistMovies() = getWatchlistMoviesUseCase()

}
