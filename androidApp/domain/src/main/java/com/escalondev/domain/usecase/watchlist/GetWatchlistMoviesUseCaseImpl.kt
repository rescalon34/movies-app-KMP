package com.escalondev.domain.usecase.watchlist

import com.escalondev.domain.mapper.movie.toMovie
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.escalondev.movies_app_kmp.domain.util.mapToDomainResult

/**
 * UseCase to fetch Watchlist from the API.
 */
class GetWatchlistMoviesUseCaseImpl(
    private val moviesUseCaseProvider: MoviesUseCaseProvider
) : GetWatchlistMoviesUseCase {

    override suspend fun invoke(
        sortBy: String,
        language: String
    ): NetworkResult<List<Movie>> {
        return moviesUseCaseProvider.getWatchlistMovies(
            sortBy = sortBy,
            language = language
        ).mapToDomainResult { data -> data.map { it.toMovie() } }
    }
}
