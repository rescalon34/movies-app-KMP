package com.escalondev.movies_app_kmp.domain.repository

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.model.SharedVideo
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

internal interface SharedMoviesRepository {

    fun getWatchlist(): Flow<List<SharedMovie>>

    suspend fun getMovies(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<SharedMovie>>

    suspend fun getWatchlistMovies(
        sortBy: String,
        language: String
    ): NetworkResult<List<SharedMovie>>

    suspend fun getVideosByMovie(
        movieId: Int
    ): NetworkResult<List<SharedVideo>>
}
