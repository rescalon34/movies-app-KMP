package com.escalondev.movies_app_kmp.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.repository.SharedMoviesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
internal class GetWatchlistUseCaseImpl(
    private val moviesRepository: SharedMoviesRepository
) : GetWatchlistUseCase {

    override fun invoke(): Flow<List<SharedMovie>> = moviesRepository.getWatchlist()
}
