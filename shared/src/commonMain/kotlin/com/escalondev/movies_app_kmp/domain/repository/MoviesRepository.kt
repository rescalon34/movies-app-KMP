package com.escalondev.movies_app_kmp.domain.repository

import com.escalondev.movies_app_kmp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

internal interface MoviesRepository {

    fun getWatchlist(): Flow<List<Movie>>
}
