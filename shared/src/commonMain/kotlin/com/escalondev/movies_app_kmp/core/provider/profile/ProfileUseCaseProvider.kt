package com.escalondev.movies_app_kmp.core.provider.profile

import com.escalondev.movies_app_kmp.domain.model.SharedProfile
import com.escalondev.movies_app_kmp.domain.util.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface ProfileUseCaseProvider {

    /**
     * @return a [NetworkResult] object Where `T` represents a [SharedProfile]
     * filtered by category.
     */
    @NativeCoroutines
    suspend fun getProfile(): NetworkResult<SharedProfile>
}
