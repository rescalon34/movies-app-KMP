package com.escalondev.movies_app_kmp.data.networking.manager

import com.escalondev.movies_app_kmp.data.networking.api.MoviesApi

internal interface NetworkingManager {
    fun getApi(): MoviesApi
}
