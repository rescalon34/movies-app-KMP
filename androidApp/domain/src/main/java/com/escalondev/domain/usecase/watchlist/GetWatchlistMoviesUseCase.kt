package com.escalondev.domain.usecase.watchlist

import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

interface GetWatchlistMoviesUseCase {

    suspend operator fun invoke(
        sortBy: String, language: String
    ): NetworkResult<List<Movie>>
}
