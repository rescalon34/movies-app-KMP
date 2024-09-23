package com.escalondev.movies_app_kmp.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Manages and configures the Ktor [HttpClient].
 *  *
 * This class sets up the [HttpClient] with features like content negotiation for JSON,
 * ensuring unknown keys are ignored in responses.
 *
 * @property httpClient The expected [HttpClient] to configure.
 * @see ktorHttpClient to know how the right object is passed via the expected/actual pattern.
 */
internal class NetworkingManagerImpl(
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
