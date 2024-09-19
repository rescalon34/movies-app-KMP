package com.escalondev.movies_app_kmp.data.networking

import io.ktor.client.HttpClient

interface NetworkingManager {
    fun getKtorClient(): HttpClient
}
