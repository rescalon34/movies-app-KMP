package com.escalondev.movies_app_kmp.data.networking

import io.ktor.client.HttpClient

internal interface NetworkingManager {
    fun getKtorClient(): HttpClient
}
