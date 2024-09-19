package com.escalondev.movies_app_kmp.domain.usecase

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

internal class GetWatchlistUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetWatchlistUseCase {

    override fun invoke(): Flow<List<Movie>> = moviesRepository.getWatchlist()
}
