@file:Suppress("DEPRECATION")

package com.escalondev.movies_app_kmp.core.manager

import com.escalondev.movies_app_kmp.core.provider.UseCaseProvider
import com.escalondev.movies_app_kmp.core.provider.UseCaseProviderImpl
import com.escalondev.movies_app_kmp.data.di.sharedCoreModule
import org.koin.core.context.startKoin

/**
 * This CoreManager is responsible for initializing internal shared dependencies as well as exposing
 * the functions that will be used from the Native clients.
 */
class SharedCoreManager {

    /**
     * Initialization will be done through static instance only.
     */
    companion object {

        /**
         * Entry initialization function exposed to the clients, any other internal initialization
         * must be done within this init function.
         */
        fun init() {
            initKoinModules()
        }

        /**
         * Init Koin Module dependencies.
         */
        private fun initKoinModules() {
            startKoin {
                modules(sharedCoreModule)
            }
        }
    }

    /**
     **************** Functions exposed to thought the SDK. ****************
     */

    /**
     * Exposed useCaseProvider to have access to any UseCase function from the Shared SDK.
     */
    val useCaseProvider: UseCaseProvider = UseCaseProviderImpl()
}
