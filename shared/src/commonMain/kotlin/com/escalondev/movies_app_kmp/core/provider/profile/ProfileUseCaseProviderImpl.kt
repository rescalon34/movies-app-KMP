package com.escalondev.movies_app_kmp.core.provider.profile

import com.escalondev.movies_app_kmp.domain.model.SharedProfile
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import org.koin.core.component.KoinComponent

/**
 * Provides access to profile-related data from internal repositories.
 * This implementation is created on the ShareCoreManager to expose profile-specific functionality to consumers.
 */
internal class ProfileUseCaseProviderImpl : KoinComponent, ProfileUseCaseProvider {

    override suspend fun getProfile(): NetworkResult<SharedProfile> {
        return NetworkResult.Success(
            SharedProfile(
                "rescalon34",
                "image"
            )
        )
    }
}
