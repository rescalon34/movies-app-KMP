package com.escalondev.movies_app_kmp.domain.usecase.home

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.repository.SharedMoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
internal class GetMoviesUseCaseImpl(
    private val moviesRepository: SharedMoviesRepository
) : GetMoviesUseCase {

    override suspend fun invoke(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<SharedMovie>> = moviesRepository.getMovies(
        category = category,
        page = page,
        language = language
    )
}
