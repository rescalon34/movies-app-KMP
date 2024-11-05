package com.escalondev.domain.usecase.home

import com.escalondev.domain.mapper.movie.toMovie
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.escalondev.movies_app_kmp.domain.util.mapToDomainResult

/**
 * UseCase to fetch Watchlist from the API.
 */
class GetMoviesUseCaseImpl(
    private val moviesUseCaseProvider: MoviesUseCaseProvider
) : GetMoviesUseCase {

    override suspend fun invoke(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<Movie>> = moviesUseCaseProvider.getMovies(
        category = category,
        page = page,
        language = language
    ).mapToDomainResult { data -> data.map { it.toMovie() } }
}
