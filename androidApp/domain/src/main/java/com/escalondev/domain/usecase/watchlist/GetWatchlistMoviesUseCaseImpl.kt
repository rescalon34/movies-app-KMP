package com.escalondev.domain.usecase.watchlist

import com.escalondev.domain.mapper.movie.toMovie
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProvider
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.escalondev.movies_app_kmp.domain.util.mapToDomainResult

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
class GetWatchlistMoviesUseCaseImpl(
    private val sharedUseCaseProvider: SharedUseCaseProvider
) : GetWatchlistMoviesUseCase {

    override suspend fun invoke(
        sortBy: String,
        language: String
    ): NetworkResult<List<Movie>> {
        return sharedUseCaseProvider.getWatchlistMovies(
            sortBy = sortBy,
            language = language
        ).mapToDomainResult { data -> data.map { it.toMovie() } }
    }
}
