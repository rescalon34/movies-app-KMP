package com.escalondev.domain.usecase.home

import com.escalondev.domain.mapper.movie.toMovie
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProvider
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.escalondev.movies_app_kmp.domain.util.mapToDomainResult

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
class GetMoviesUseCaseImpl(
    private val sharedUseCaseProvider: SharedUseCaseProvider
) : GetMoviesUseCase {

    override suspend fun invoke(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<Movie>> = sharedUseCaseProvider.getMovies(
        category = category,
        page = page,
        language = language
    ).mapToDomainResult { data -> data.map { it.toMovie() } }
}
