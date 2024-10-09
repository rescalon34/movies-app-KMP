package com.escalondev.movies_app_kmp.data.repository

import com.escalondev.movies_app_kmp.data.model.MovieDataResponse
import com.escalondev.movies_app_kmp.data.networking.manager.NetworkingManager
import com.escalondev.movies_app_kmp.data.networking.mapToResponse
import com.escalondev.movies_app_kmp.data.networking.safeApiRequest
import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository responsible for handling all networking operations related to the Movies feature.
 */
internal class MoviesRepositoryImpl(
    private val networkingManager: NetworkingManager
) : MoviesRepository {

    override fun getWatchlist(): Flow<List<Movie>> {
        return flow {
            // Mimic API call for now.
            delay(1000)
            emit(MockedMoviesRepository.getWatchlist())
        }
    }

    override suspend fun getWatchlistMovies(sortBy: String): NetworkResult<List<Movie>> {
        return safeApiRequest {
            networkingManager.getApi()
                .getWatchlistMovies(
                    accountId = 0,
                    sortBy = sortBy
                ).mapToResponse<MovieDataResponse>()
        }
    }
}
