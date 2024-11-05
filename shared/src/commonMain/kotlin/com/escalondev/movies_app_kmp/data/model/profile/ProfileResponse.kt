package com.escalondev.movies_app_kmp.data.model.profile

import com.escalondev.movies_app_kmp.data.mapper.DomainMapper
import com.escalondev.movies_app_kmp.domain.model.SharedProfile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileResponse(
    val id: Int,
    val name: String?,
    val username: String?,
    @SerialName("avatar")
    val avatarResponse: AvatarResponse? = null,
) : DomainMapper<SharedProfile> {
    override fun toDomain() = SharedProfile(
        name = name,
        username = username,
        imageUrl = avatarResponse?.tmdb?.avatarPath
    )
}

@Serializable
internal data class AvatarResponse(
    @SerialName("tmdb")
    val tmdb: TmdbResponse
)

@Serializable
internal data class TmdbResponse(
    @SerialName("avatar_path")
    val avatarPath: String
)
