package com.escalondev.movies_app_kmp.domain.usecase.home

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
internal class GetMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetMoviesUseCase {

    override suspend fun invoke(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<Movie>> = moviesRepository.getMovies(
        category = category,
        page = page,
        language = language
    )
}
