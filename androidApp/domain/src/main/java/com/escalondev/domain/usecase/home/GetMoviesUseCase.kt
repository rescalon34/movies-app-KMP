package com.escalondev.domain.usecase.home

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

interface GetMoviesUseCase {

    suspend operator fun invoke(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<SharedMovie>>
}
