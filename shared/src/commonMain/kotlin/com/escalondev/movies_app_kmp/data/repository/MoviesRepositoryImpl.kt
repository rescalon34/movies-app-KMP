package com.escalondev.movies_app_kmp.data.repository

import com.escalondev.movies_app_kmp.data.networking.NetworkingManager
import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val networkingManager: NetworkingManager
) : MoviesRepository {

    override fun getWatchlist(): Flow<List<Movie>> {
        networkingManager.getKtorClient()
        return flow {
            // Mimic API call for now.
            delay(1000)
            emit(MockedMoviesRepository.getWatchlist())
        }
    }
}
