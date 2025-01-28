package com.escalondev.movies_app_kmp.data.model.video

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.SharedVideo
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    val id: String,
    val name: String,
    val key: String
) : DomainMapper<SharedVideo> {
    override fun toDomain() = SharedVideo(
        id = id,
        name = name,
        key = key
    )
}
