@file:Suppress("DEPRECATION")

package com.escalondev.movies_app_kmp.core.manager

import com.escalondev.movies_app_kmp.core.provider.factory.SharedUseCaseProviderFactory
import com.escalondev.movies_app_kmp.core.provider.factory.SharedUseCaseProviderFactoryImpl
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import com.escalondev.movies_app_kmp.core.provider.profile.ProfileUseCaseProvider
import com.escalondev.movies_app_kmp.data.di.sharedCoreModule
import org.koin.core.context.startKoin
import kotlin.jvm.Synchronized

/**
 * This CoreManager is responsible for initializing internal shared dependencies as well as exposing
 * the other classes that will be used from the Native clients.
 */
class SharedCoreManager private constructor() {

    /**
     * Object to store in memory the provided API Key and AccessToken from the consumers.
     */
    private val tokenManager: TokenManager = TokenManager

    /**
     * Will allow to create instances of UseCaseProviders.
     */
    private val factoryProvider: SharedUseCaseProviderFactory = SharedUseCaseProviderFactoryImpl()

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
            .apply {
                this.apiKey = apiKey
                this.accessToken = accessToken
            }
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
     * Set API Key, Intended to be used when this value is not available at SDK initialization time.
     */
    fun setApiKey(key: String) {
        tokenManager.apiKey = key
    }

    /**
     * Set Access Token, Intended to be used when this value is not available at SDK initialization time.
     */
    fun setAccessToken(token: String) {
        tokenManager.accessToken = token
    }

    /**
     * This function allows a smart way to create a UseCaseProvider by specifying the type of UseCase
     * to return when the function is called.
     */
    private inline fun <reified T : Any> createUseCaseProvider(): T =
        factoryProvider.createProvider(T::class)

    /**
     * Provides specific functions to create instances of each UseCaseProvider, that works well on
     * cross-platform compatibility.
     *
     * These functions allow consumers to get the corresponding UseCaseProvider instance without
     * needing generics. This approach is preferred for iOS interoperability, as it avoids the
     * limitations of inline functions and reified types, which are unsupported in Swift and Objective-C
     * when using Kotlin Multiplatform.
     */
    fun createMoviesUseCaseProvider(): MoviesUseCaseProvider = createUseCaseProvider()

    fun createProfileUseCaseProvider(): ProfileUseCaseProvider = createUseCaseProvider()

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
}
