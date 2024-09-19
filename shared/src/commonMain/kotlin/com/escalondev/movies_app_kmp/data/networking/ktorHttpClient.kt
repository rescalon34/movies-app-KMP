package com.escalondev.movies_app_kmp.data.networking

import io.ktor.client.HttpClient

/**
 * Expected Implementation for the Ktor HttpClient.
 * The Actual implementation will be delegated by each native platform.
 */
internal expect val ktorHttpClient: HttpClient