package com.escalondev.movies_app_kmp.core.di

import com.escalondev.movies_app_kmp.core.SharedCoreManager
import org.koin.dsl.module

/**
 * Module intended to create all the shared dependencies that will be exposed to
 * the iOS side.
 */
val coreModule = module {
    single<SharedCoreManager> { SharedCoreManager() }
}
