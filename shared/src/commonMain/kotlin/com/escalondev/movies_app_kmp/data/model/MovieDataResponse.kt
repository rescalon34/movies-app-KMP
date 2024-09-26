package com.escalondev.movies_app_kmp.data.model

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDataResponse(
    @SerialName("results")
    val results: List<MovieResponse>
) : DomainMapper<List<Movie>> {
    override fun toDomain(): List<Movie> {
        return results.map { it.toDomain() }
    }
}
