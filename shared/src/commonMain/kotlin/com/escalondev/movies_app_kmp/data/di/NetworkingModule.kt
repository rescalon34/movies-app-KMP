package com.escalondev.movies_app_kmp.data.di

import com.escalondev.movies_app_kmp.data.networking.NetworkingManager
import com.escalondev.movies_app_kmp.data.networking.NetworkingManagerImpl
import com.escalondev.movies_app_kmp.data.networking.ktorHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

/**
 * This module host all networking object instances within the Shared Module.
 */
internal val networkingModule = module {
    single<HttpClient> { ktorHttpClient }
    single<NetworkingManager> { NetworkingManagerImpl(httpClient = get()) }
}
