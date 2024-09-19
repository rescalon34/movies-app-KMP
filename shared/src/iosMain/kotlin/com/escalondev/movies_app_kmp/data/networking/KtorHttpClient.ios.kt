package com.escalondev.movies_app_kmp.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

/**
 * Native iOS Implementation for the Ktor HttpClient.
 */
actual val ktorHttpClient = HttpClient(Darwin)
