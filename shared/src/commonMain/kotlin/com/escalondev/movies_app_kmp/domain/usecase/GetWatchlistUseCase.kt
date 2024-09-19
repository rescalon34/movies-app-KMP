package com.escalondev.movies_app_kmp.domain.usecase

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

interface GetWatchlistUseCase {

    @NativeCoroutines
    fun getWatchlist(): Flow<List<Movie>>
}
