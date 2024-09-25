package com.escalondev.movies_app_kmp.data.networking.api

import com.escalondev.movies_app_kmp.data.model.MovieDataResponse
import com.escalondev.movies_app_kmp.data.model.base.BaseHttpResponse

internal interface MoviesApi {

    suspend fun getWatchlistMovies(accountId: Int): BaseHttpResponse<MovieDataResponse>
}
