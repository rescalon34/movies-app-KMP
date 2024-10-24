package com.escalondev.domain.usecase.watchlist

import com.escalondev.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface GetWatchlistUseCase {

    operator fun invoke(): Flow<List<Movie>>
}
