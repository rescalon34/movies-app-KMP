package com.escalondev.movies_app_kmp.core.provider

import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

/**
 * SharedUseCaseProvider facilitates access to functions that Native clients use to request data
 * from the API through the Shared SDK.
 */
interface SharedUseCaseProvider {

    /**
     *
     * This function emits updates to the watchlist as a [Flow] of [List] of [SharedMovie].
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
     * @return [Flow] emitting a list of [SharedMovie] objects.
     */
    @NativeCoroutines
    fun getWatchlist(): Flow<List<SharedMovie>>

    /**
     *
     * Collect the data/error using thought the `onSuccess()` or `onFailure()` functions.
     *
     * iOS sample usage:
     * ```
     * // your reactive code, publisher, etc.
     * response.onSuccess { data in
     *     let movies = data as? [Movie] ?? []
     *     // rest of the code
     * }
     * .onFailure { errorMessage in
     *    // rest of the code
     * }
     * ```
     *
     * Android sample usage:
     * ```
     * sharedCoreManager.useCaseProvider.getWatchlistMovies()
     *      .onSuccess { watchlist ->
     *          // rest of the code
     *      }
     *      .onFailure { error ->
     *          // rest of the code
     *      }
     * }
     * ```
     *
     * @return a [NetworkResult] object Where `T` represents a [List] of [SharedMovie].
     */
    @NativeCoroutines
    suspend fun getWatchlistMovies(
        sortBy: String,
        language: String
    ): NetworkResult<List<SharedMovie>>

    /**
     * @return a [NetworkResult] object Where `T` represents a [List] of [SharedMovie]
     * filtered by category.
     */
    @NativeCoroutines
    suspend fun getMovies(
        category: String,
        page: Int,
        language: String
    ): NetworkResult<List<SharedMovie>>
}
