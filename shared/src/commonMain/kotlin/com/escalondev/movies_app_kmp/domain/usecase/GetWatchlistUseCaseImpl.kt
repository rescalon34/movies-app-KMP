package com.escalondev.movies_app_kmp.domain.usecase

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class GetWatchlistUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetWatchlistUseCase {

    @NativeCoroutines
    override fun getWatchlist(): Flow<List<Movie>> = moviesRepository.getWatchlist()
}
