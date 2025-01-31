package com.escalondev.movies_app_kmp.data.model.video

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.SharedVideo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class VideoDataResponse(
    @SerialName("results")
    val results: List<VideoResponse>
) : DomainMapper<List<SharedVideo>> {
    override fun toDomain(): List<SharedVideo> {
        return results.map { it.toDomain() }
    }
}
