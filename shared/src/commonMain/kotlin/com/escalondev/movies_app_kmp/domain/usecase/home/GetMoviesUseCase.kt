package com.escalondev.movies_app_kmp.domain.usecase.home

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

internal interface GetMoviesUseCase {

    suspend operator fun invoke(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<Movie>>
}
