package com.escalondev.movies_app_kmp.data.networking.api

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import de.jensklingenberg.ktorfit.http.Url
import io.ktor.client.statement.HttpResponse

internal interface MoviesApi {

    @GET("account/{account_id}/watchlist/movies")
    suspend fun getWatchlistMovies(
        @Path("account_id") accountId: Int,
        @Query("sort_by") sortBy: String,
        @Query("language") language: String,
    ): HttpResponse

    @GET
    suspend fun getMovies(
        @Url url: String,
        @Query("page") page: Int? = null,
        @Query("language") language: String,
    ): HttpResponse

    @GET("account/{account_id}")
    suspend fun getProfile(@Path("account_id") accountId: Int): HttpResponse
}
