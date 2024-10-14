package com.escalondev.movies_app_kmp.domain.repository

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

internal interface MoviesRepository {

    fun getWatchlist(): Flow<List<Movie>>

    suspend fun getMovies(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<Movie>>

    suspend fun getWatchlistMovies(
        sortBy: String,
        language: String
    ): NetworkResult<List<Movie>>
}
