package com.escalondev.movies_app_kmp.data.model

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieResponse(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val imageUrl: String?
) : DomainMapper<Movie> {
    override fun toDomain() = Movie(
        id = id,
        title = title,
        imageUrl = imageUrl
    )
}
