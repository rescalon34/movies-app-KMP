package com.escalondev.domain.usecase.profile

import com.escalondev.domain.model.profile.Profile
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

interface GetProfileUseCase {

    suspend operator fun invoke(): NetworkResult<Profile>
}
