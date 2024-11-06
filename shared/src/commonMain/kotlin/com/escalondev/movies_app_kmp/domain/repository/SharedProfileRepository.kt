package com.escalondev.movies_app_kmp.domain.repository

import com.escalondev.movies_app_kmp.domain.model.SharedProfile
import com.escalondev.movies_app_kmp.domain.util.NetworkResult

internal interface SharedProfileRepository {

    suspend fun getProfile(): NetworkResult<SharedProfile>
}
