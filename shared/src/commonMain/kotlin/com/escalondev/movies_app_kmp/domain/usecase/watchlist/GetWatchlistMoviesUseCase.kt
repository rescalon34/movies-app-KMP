package com.escalondev.movies_app_kmp.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

internal interface GetWatchlistMoviesUseCase {

    suspend operator fun invoke(sortBy: String, language: String): NetworkResult<List<Movie>>
}
