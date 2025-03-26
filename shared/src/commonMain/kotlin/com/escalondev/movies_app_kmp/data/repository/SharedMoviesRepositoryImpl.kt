package com.escalondev.movies_app_kmp.data.repository

import com.escalondev.movies_app_kmp.data.model.movie.MovieDataResponse
import com.escalondev.movies_app_kmp.data.model.video.VideoDataResponse
import com.escalondev.movies_app_kmp.data.networking.manager.NetworkingManager
import com.escalondev.movies_app_kmp.data.networking.mapToResponse
import com.escalondev.movies_app_kmp.data.networking.safeApiRequest
import com.escalondev.movies_app_kmp.data.util.getMovieEndpointByCategory
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.model.SharedVideo
import com.escalondev.movies_app_kmp.domain.repository.SharedMoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository responsible for handling all networking operations related to the Movies feature.
 */
internal class SharedMoviesRepositoryImpl(
    private val networkingManager: NetworkingManager
) : SharedMoviesRepository {

    override fun getWatchlist(): Flow<List<SharedMovie>> {
        return flow {
            // Mimic API call for now.
            delay(1000)
            emit(MockedMoviesRepository.getWatchlist())
        }
    }

    override suspend fun getWatchlistMovies(
        sortBy: String,
        language: String
    ): NetworkResult<List<SharedMovie>> {
        return safeApiRequest {
            networkingManager.getApi()
                .getWatchlistMovies(
                    accountId = 0,
                    sortBy = sortBy,
                    language = language
                ).mapToResponse<MovieDataResponse>()
        }
    }

    override suspend fun getMovies(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<SharedMovie>> {
        return safeApiRequest {
            networkingManager.getApi()
                .getMovies(
                    url = category.getMovieEndpointByCategory(),
                    page = page,
                    language = language
                ).mapToResponse<MovieDataResponse>()
        }
    }

    override suspend fun getVideosByMovie(movieId: Int): NetworkResult<List<SharedVideo>> {
        return safeApiRequest {
            networkingManager.getApi()
                .getVideosByMovie(movieId = movieId)
                .mapToResponse<VideoDataResponse>()
        }
    }
}
