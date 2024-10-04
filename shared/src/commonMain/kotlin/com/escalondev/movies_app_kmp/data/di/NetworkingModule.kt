package com.escalondev.movies_app_kmp.data.di

import com.escalondev.movies_app_kmp.data.networking.expectactual.ktorHttpClient
import com.escalondev.movies_app_kmp.data.networking.manager.NetworkingManager
import com.escalondev.movies_app_kmp.data.networking.manager.NetworkingManagerImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

/**
 * This module host all networking object instances within the Shared Module.
 */
internal val networkingModule = module {
    single<HttpClient> { ktorHttpClient }
    single<NetworkingManager> { NetworkingManagerImpl(httpClient = get()) }
}
