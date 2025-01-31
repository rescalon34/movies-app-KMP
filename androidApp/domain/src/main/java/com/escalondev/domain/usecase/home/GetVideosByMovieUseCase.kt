package com.escalondev.domain.usecase.home

import com.escalondev.domain.model.Video
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

interface GetVideosByMovieUseCase {

    suspend operator fun invoke(
        movieId: Int,
    ): NetworkResult<List<Video>>
}
