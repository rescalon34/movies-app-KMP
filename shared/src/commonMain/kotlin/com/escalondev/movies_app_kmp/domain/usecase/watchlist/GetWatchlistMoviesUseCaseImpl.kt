package com.escalondev.movies_app_kmp.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
internal class GetWatchlistMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetWatchlistMoviesUseCase {

    override suspend fun invoke(sortBy: String): NetworkResult<List<Movie>> {
        return moviesRepository.getWatchlistMovies(sortBy = sortBy)
    }
}
