package com.escalondev.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProvider
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import kotlinx.coroutines.flow.Flow

/**
 * Internal UseCase to fetch Watchlist from the API.
 */
class GetWatchlistUseCaseImpl(
    private val sharedUseCaseProvider: SharedUseCaseProvider
) : GetWatchlistUseCase {

    override fun invoke(): Flow<List<SharedMovie>> = sharedUseCaseProvider.getWatchlist()
}
