package com.escalondev.movies_app_kmp.core.provider

import com.escalondev.movies_app_kmp.domain.model.Movie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to shared use cases, exposed for consumers but with restricted instantiation
 * to ensure better control.
 *
 * Access this provider via `SharedCoreManager.useCaseProvider` instead of direct instantiation.
 */
@Deprecated("Direct instantiation is discouraged. Access via `SharedCoreManager.useCaseProvider`.")
interface UseCaseProvider {

    /**
     * Returns a Flow of the user's watchlist.
     *
     * This function emits updates to the watchlist as a [Flow] of [List] of [Movie].
     * Use this to observe changes in the watchlist over time.
     *
     * iOS sample usage:
     * ```
     * sharedCoreManager.useCaseProvider.invoke { manager, provider in
     *     createPublisher(for: manager.getWatchlist(provider))
     *     // rest of the code.
     * }
     * ```
     *
     * Android sample usage:
     * ```
     * sharedCoreManager.useCaseProvider.getWatchlist().collectLatest { watchlist ->
     *     // rest of the code
     * }
     * ```
     *
     * @return [Flow] emitting a list of [Movie] objects.
     */
    @NativeCoroutines
    fun getWatchlist(): Flow<List<Movie>>

    @NativeCoroutines
    suspend fun getWatchlistMovies(): NetworkResult<List<Movie>>
}
