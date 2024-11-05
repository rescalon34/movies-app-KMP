package com.escalondev.movies_app_kmp.data.repository

import com.escalondev.movies_app_kmp.data.model.profile.ProfileResponse
import com.escalondev.movies_app_kmp.data.networking.manager.NetworkingManager
import com.escalondev.movies_app_kmp.data.networking.mapToResponse
import com.escalondev.movies_app_kmp.data.networking.safeApiRequest
import com.escalondev.movies_app_kmp.domain.model.SharedProfile
import com.escalondev.movies_app_kmp.domain.repository.SharedProfileRepository
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

/**
 * Repository responsible for handling all networking operations related to the Profile feature.
 */
internal class SharedProfileRepositoryImpl(
    private val networkingManager: NetworkingManager
) : SharedProfileRepository {

    override suspend fun getProfile(): NetworkResult<SharedProfile> {
        return safeApiRequest {
            networkingManager.getApi().getProfile(0)
                .mapToResponse<ProfileResponse>()
        }
    }
}
