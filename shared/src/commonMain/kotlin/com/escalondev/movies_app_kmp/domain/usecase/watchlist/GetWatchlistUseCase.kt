package com.escalondev.movies_app_kmp.domain.usecase.watchlist

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import kotlinx.coroutines.flow.Flow

internal interface GetWatchlistUseCase {

    operator fun invoke(): Flow<List<SharedMovie>>
}
