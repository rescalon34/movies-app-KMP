package com.escalondev.domain.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Movie(
    val id: Int = 0,
    val title: String? = "",
    val imageUrl: String? = "",
    val releaseDate: String? = "",
    val overview: String? = "",
) : Parcelable
