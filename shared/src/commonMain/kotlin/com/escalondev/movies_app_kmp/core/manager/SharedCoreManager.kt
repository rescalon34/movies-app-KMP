@file:Suppress("DEPRECATION")

package com.escalondev.movies_app_kmp.core.manager

import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProvider
import com.escalondev.movies_app_kmp.core.provider.SharedUseCaseProviderImpl
import com.escalondev.movies_app_kmp.data.di.sharedCoreModule
import org.koin.core.context.startKoin
import kotlin.jvm.Synchronized

/**
 * This CoreManager is responsible for initializing internal shared dependencies as well as exposing
 * the functions that will be used from the Native clients.
 */
class SharedCoreManager private constructor() {

    /**
     * Provides a single instance of the SharedCoreManager class.
     */
    companion object {
        private var instance: SharedCoreManager? = null

        @Synchronized
        fun getInstance(): SharedCoreManager {
            if (instance == null) {
                instance = SharedCoreManager()
            }
            return instance ?: throw IllegalStateException("Couldn't initialize SharedCoreManager")
        }
    }

    /**
     * Entry initialization function exposed to the clients, any other internal initialization
     * must be done within this init function.
     */
    fun init() {
        initKoinModules()
    }

    /**
     * This function allows passing apiKey and accessToken to the SharedCoreManager at initialization
     * time, if keys are provided then the API will be able to make API calls.
     */
    fun init(apiKey: String, accessToken: String) {
        initKoinModules()
        tokenManager
            .setApiKey(apiKey)
            .setAccessToken(accessToken)
    }

    /**
     * Init Koin Module dependencies.
     */
    private fun initKoinModules() {
        startKoin {
            modules(sharedCoreModule)
        }
    }

    /**
     **************** Functions exposed to thought the SDK. ****************
     */

    /**
     * Exposed useCaseProvider to have access to any UseCase function from the Shared SDK.
     */
    val useCaseProvider: SharedUseCaseProvider = SharedUseCaseProviderImpl()

    private val tokenManager: TokenManager = TokenManager
}
