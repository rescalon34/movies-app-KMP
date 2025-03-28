package com.escalondev.movies_app_kmp.data.networking.expectactual

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

/**
 * Native iOS Implementation for the Ktor HttpClient.
 */
internal actual val ktorHttpClient = HttpClient(Darwin)
