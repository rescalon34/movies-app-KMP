package com.escalondev.movies_app_kmp.data.repository

import com.escalondev.movies_app_kmp.data.networking.api.MoviesApi
import com.escalondev.movies_app_kmp.data.networking.safeApiRequest
import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository responsible for handling all networking operations related to the Movies feature.
 *
 * @param api Interface for making API calls related to Movies.
 */
internal class MoviesRepositoryImpl(
    private val api: MoviesApi
) : MoviesRepository {

    override fun getWatchlist(): Flow<List<Movie>> {
        return flow {
            // Mimic API call for now.
            delay(1000)
            emit(MockedMoviesRepository.getWatchlist())
        }
    }

    override suspend fun getWatchlistMovies(): NetworkResult<List<Movie>> {
        return safeApiRequest { api.getWatchlistMovies(0) }
    }
}
