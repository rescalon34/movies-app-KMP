package com.escalondev.domain.usecase.home

import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProvider
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

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
    ): NetworkResult<List<SharedMovie>> = sharedUseCaseProvider.getMovies(
        category = category,
        page = page,
        language = language
    )
}
