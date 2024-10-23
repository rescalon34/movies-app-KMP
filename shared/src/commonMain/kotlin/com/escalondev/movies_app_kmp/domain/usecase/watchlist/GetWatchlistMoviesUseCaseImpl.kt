package com.escalondev.movies_app_kmp.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.repository.SharedMoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
internal class GetWatchlistMoviesUseCaseImpl(
    private val moviesRepository: SharedMoviesRepository
) : GetWatchlistMoviesUseCase {

    override suspend fun invoke(sortBy: String, language: String): NetworkResult<List<SharedMovie>> {
        return moviesRepository.getWatchlistMovies(sortBy = sortBy, language = language)
    }
}
