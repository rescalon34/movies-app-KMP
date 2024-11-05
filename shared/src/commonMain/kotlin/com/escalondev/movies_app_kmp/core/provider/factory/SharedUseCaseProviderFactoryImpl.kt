package com.escalondev.movies_app_kmp.core.provider.factory

import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProvider
import com.escalondev.movies_app_kmp.core.provider.movie.MoviesUseCaseProviderImpl
import com.escalondev.movies_app_kmp.core.provider.profile.ProfileUseCaseProvider
import com.escalondev.movies_app_kmp.core.provider.profile.ProfileUseCaseProviderImpl

/**
 * A factory implementation to create instances of UseCaseProviders based on a specified provider type.
 * This class provides a flexible way to instantiate various UseCaseProvider implementations
 * based on their class type.
 */
@Suppress("UNCHECKED_CAST")
internal class SharedUseCaseProviderFactoryImpl : SharedUseCaseProviderFactory {

    /**
     * Creates an instance of the requested UseCaseProvider.
     *
     * @param providerType Specifies the type of UseCaseProvider to create.
     * @return An instance of the requested UseCaseProvider, cast to the expected type.
     * @throws IllegalArgumentException If the provided type does not match any known UseCaseProvider.
     */
    override fun <T : Any> createProvider(providerType: Any): T {
        return when (providerType) {
            MoviesUseCaseProvider::class -> MoviesUseCaseProviderImpl() as T
            ProfileUseCaseProvider::class -> ProfileUseCaseProviderImpl() as T
            else -> throw IllegalArgumentException("Unknown provider type: ${this::class}")
        }
    }
}
