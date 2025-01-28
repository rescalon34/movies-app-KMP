package com.escalondev.movies_app_kmp.data.model.movie

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.SharedMovie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieResponse(
    val id: Int,
    val title: String?,
    @SerialName("poster_path")
    val imageUrl: String?,
    @SerialName("release_date")
    val releaseDate: String?,
) : DomainMapper<SharedMovie> {
    override fun toDomain() = SharedMovie(
        id = id,
        title = title,
        imageUrl = imageUrl,
        releaseDate = releaseDate
    )
}
