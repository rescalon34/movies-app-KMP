package com.escalondev.movies_app_kmp.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

/**
 * Native Android Implementation for the Ktor HttpClient.
 */
internal actual val ktorHttpClient = HttpClient(OkHttp)
