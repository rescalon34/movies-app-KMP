package com.escalondev.movies_app_kmp.data.networking.manager

import com.escalondev.movies_app_kmp.core.manager.TokenManager
import com.escalondev.movies_app_kmp.data.networking.api.MoviesApi
import com.escalondev.movies_app_kmp.data.networking.api.createMoviesApi
import com.escalondev.movies_app_kmp.data.networking.expectactual.ktorHttpClient
import com.escalondev.movies_app_kmp.data.networking.getJsonDecoder
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

/**
 * Manages and configures the Ktor [HttpClient].
 *
 * Sets up the [HttpClient] with features such as content negotiation for JSON, ensuring
 * that unknown keys in the response are ignored.
 *
 * @property httpClient The [HttpClient] expected to be configured.
 * @see ktorHttpClient for how the expected object is passed using the expected/actual pattern.
 */
internal class NetworkingManagerImpl(
    private val httpClient: HttpClient
) : NetworkingManager {

    override fun getApi(): MoviesApi {
        return moviesApi
    }

    private fun buildKtorClient(): HttpClient {
        return httpClient.config {
            defaultRequest { url(BASE_URL) }

            // Json parsing
            install(ContentNegotiation) {
                json(json = getJsonDecoder())
            }

            install(Logging) {
                logger = getNetworkLogger()
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                header(AUTHORIZATION, "$BEARER${TokenManager.accessToken}")
                header(API_KEY, "${TokenManager.apiKey}")
            }

            install(Auth) {
                bearer {
                    sendWithoutRequest { false }
                    loadTokens {
                        BearerTokens(BEARER, "${TokenManager.apiKey}")
                    }
                }
            }
        }
    }

    private val moviesApi = Ktorfit.Builder()
        .baseUrl(BASE_URL)
        .httpClient(buildKtorClient())
        .build()
        .createMoviesApi()

    /**
     * Custom networking logger.
     */
    private fun getNetworkLogger() = object : Logger {
        override fun log(message: String) {
            println("$TAG: $message")
        }
    }

    companion object {
        private var TAG = "NetworkingManager"
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
        const val BEARER = "Bearer "
        const val AUTHORIZATION = "Authorization"
        const val API_KEY = "api_key"
    }
}
