package com.escalondev.movies_app_kmp.core.provider

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.repository.SharedMoviesRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * This class is intended to store all internal useCases instances for better organization.
 *
 * The public functions will be used by the consumers (Android, iOS) and the useCase will be limited
 * to internal uses within the SDK.
 */
internal class SharedUseCaseProviderImpl : KoinComponent, SharedUseCaseProvider {

    // provide access to all internal repositories within the SDK.
    private val moviesRepository: SharedMoviesRepository by inject<SharedMoviesRepository>()

    // Exposed functions for the consumers.
    override fun getWatchlist(): Flow<List<SharedMovie>> = moviesRepository.getWatchlist()

    override suspend fun getWatchlistMovies(
        sortBy: String,
        language: String
    ) = moviesRepository.getWatchlistMovies(sortBy = sortBy, language = language)

    override suspend fun getMovies(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<SharedMovie>> = moviesRepository.getMovies(
        category = category,
        page = page,
        language = language
    )
}
