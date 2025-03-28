package com.escalondev.movies_app_kmp.data.di

/**
 * Add all the common modules to this list.
 * the sharedCoreModule will be called on the Android/iOS side when initializing the DI
 * at their very first entry point.
 */
internal val sharedCoreModule = listOf(
    networkingModule,
    repositoryModule
)
