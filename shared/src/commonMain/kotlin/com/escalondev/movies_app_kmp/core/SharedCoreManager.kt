package com.escalondev.movies_app_kmp.core

import com.escalondev.movies_app_kmp.data.di.sharedCoreModule
import com.escalondev.movies_app_kmp.domain.model.Movie
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow
import org.koin.core.context.startKoin

/**
 * This CoreManager is responsible for initializing internal shared dependencies as well as exposing
 * the functions that will be used from the Native clients.
 *
 */
class SharedCoreManager {

    /**
     * Init Koin Module dependencies.
     */
    fun initKoinModule() {
        startKoin {
            modules(sharedCoreModule)
        }
    }

    /**
     **************** Functions exposed to thought the SDK. ****************
     */

    /**
     * Returns a flow of the user's watchlist.
     *
     * This function emits updates to the watchlist as a [Flow] of [List] of [Movie].
     * Use this to observe changes in the watchlist over time.
     *
     * Example:
     * ```
     * sharedCoreManager.getWatchlist().collectLatest { watchlist ->
     *     // Handle watchlist
     * }
     * ```
     *
     * @return [Flow] emitting a list of [Movie] objects.
     */
    @NativeCoroutines
    fun getWatchlist(): Flow<List<Movie>> {
        return UseCaseManager.getWatchlistUseCase()
    }
}
