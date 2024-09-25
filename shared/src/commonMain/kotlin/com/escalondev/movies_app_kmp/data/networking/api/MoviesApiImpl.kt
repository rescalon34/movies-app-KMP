package com.escalondev.movies_app_kmp.data.networking.api

import com.escalondev.movies_app_kmp.data.model.MovieDataResponse
import com.escalondev.movies_app_kmp.data.model.base.BaseHttpResponse
import com.escalondev.movies_app_kmp.data.networking.manager.NetworkingManager
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.encodedPath

/**
 * This class handles all API interactions (GET, POST, PUT, DELETE, etc.).
 *
 * Each API endpoint returns a `BaseHttpResponse<T>`, where `T` is the type to
 * which the JSON response is deserialized.
 *
 * @param networkingManager Provides access to the Ktor client for making API calls.
 */
internal class MoviesApiImpl(
    private val networkingManager: NetworkingManager
) : MoviesApi {

    override suspend fun getWatchlistMovies(
        accountId: Int
    ): BaseHttpResponse<MovieDataResponse> {
        return BaseHttpResponse(
            networkingManager.getKtorClient()
                // TODO, construct this URL by also appending the given parameters.
                .get { buildEndpoint(WATCH_LIST_ENDPOINT) }
        )
    }

    /**
     * This function will build the URL of the API Endpoint dynamically.
     */
    private fun HttpRequestBuilder.buildEndpoint(url: String) {
        url { encodedPath = url }
    }

    companion object {
        const val WATCH_LIST_ENDPOINT = "account/0/watchlist/movies"
    }
}
