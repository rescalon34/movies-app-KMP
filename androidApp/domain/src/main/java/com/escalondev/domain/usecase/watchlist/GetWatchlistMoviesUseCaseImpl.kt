package com.escalondev.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProvider
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
class GetWatchlistMoviesUseCaseImpl(
    private val sharedUseCaseProvider: SharedUseCaseProvider
) : GetWatchlistMoviesUseCase {

    override suspend fun invoke(
        sortBy: String,
        language: String
    ): NetworkResult<List<SharedMovie>> {
        return sharedUseCaseProvider.getWatchlistMovies(sortBy = sortBy, language = language)
    }
}
