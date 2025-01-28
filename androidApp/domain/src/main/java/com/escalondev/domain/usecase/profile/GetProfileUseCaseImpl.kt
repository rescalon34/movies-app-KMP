package com.escalondev.domain.usecase.profile

import com.escalondev.domain.mapper.profile.toProfile
import com.escalondev.domain.model.profile.Profile
import com.escalondev.movies_app_kmp.core.provider.profile.ProfileUseCaseProvider
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.escalondev.movies_app_kmp.domain.util.mapToDomainResult

/**
 * UseCase to fetch Profile data from the API.
 */
class GetProfileUseCaseImpl(
    private val profileUseCaseProvider: ProfileUseCaseProvider
) : GetProfileUseCase {

    override suspend fun invoke(): NetworkResult<Profile> {
        return profileUseCaseProvider.getProfile()
            .mapToDomainResult { data -> data.toProfile() }
    }
}
