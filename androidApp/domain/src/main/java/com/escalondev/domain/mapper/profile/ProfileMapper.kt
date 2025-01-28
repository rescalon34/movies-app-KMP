package com.escalondev.domain.mapper.profile

import com.escalondev.domain.model.profile.Profile
import com.escalondev.movies_app_kmp.domain.model.SharedProfile

fun SharedProfile.toProfile(): Profile {
    return Profile(
        username = username,
        name = name,
        imageUrl = imageUrl,
    )
}
