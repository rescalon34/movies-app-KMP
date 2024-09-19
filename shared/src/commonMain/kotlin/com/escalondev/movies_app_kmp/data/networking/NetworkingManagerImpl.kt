package com.escalondev.movies_app_kmp.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkingManagerImpl(
    private val httpClient: HttpClient
) : NetworkingManager {

    override fun getKtorClient(): HttpClient {
        println("httpClient: $httpClient")
        return httpClient.config {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}
