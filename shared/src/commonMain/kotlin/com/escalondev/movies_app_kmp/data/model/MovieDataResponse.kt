package com.escalondev.movies_app_kmp.data.model

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDataResponse(
    @SerialName("results")
    val results: List<MovieResponse>
) : DomainMapper<List<SharedMovie>> {
    override fun toDomain(): List<SharedMovie> {
        return results.map { it.toDomain() }
    }
}
