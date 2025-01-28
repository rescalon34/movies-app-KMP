package com.escalondev.domain.usecase.watchlist

import com.escalondev.domain.mapper.movie.toMovie
import com.escalondev.domain.model.movie.Movie
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * UseCase to fetch Watchlist from the API.
 */
class GetWatchlistUseCaseImpl(
    private val moviesUseCaseProvider: MoviesUseCaseProvider
) : GetWatchlistUseCase {

    override fun invoke(): Flow<List<Movie>> = moviesUseCaseProvider
        .getWatchlist()
        .map { data -> data.map { it.toMovie() } }
}
