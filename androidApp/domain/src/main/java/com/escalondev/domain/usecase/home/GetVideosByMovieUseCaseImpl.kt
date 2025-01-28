package com.escalondev.domain.usecase.home

import com.escalondev.domain.mapper.movie.toVideo
import com.escalondev.domain.model.Video
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.escalondev.movies_app_kmp.domain.util.mapToDomainResult

/**
 * UseCase to fetch Watchlist from the API.
 */
class GetVideosByMovieUseCaseImpl(
    private val moviesUseCaseProvider: MoviesUseCaseProvider
) : GetVideosByMovieUseCase {

    override suspend fun invoke(
        movieId: Int,
    ): NetworkResult<List<Video>> = moviesUseCaseProvider.getVideosByMovie(
        movieId = movieId
    ).mapToDomainResult { data -> data.map { it.toVideo() } }
}
