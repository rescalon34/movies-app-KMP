package com.escalondev.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import kotlinx.coroutines.flow.Flow

interface GetWatchlistUseCase {

    operator fun invoke(): Flow<List<SharedMovie>>
}
