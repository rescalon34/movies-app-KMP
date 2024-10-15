package com.escalondev.movies_app_kmp.domain.model

data class Movie(
    val id: Int,
    val title: String?,
    val imageUrl: String?,
    val releaseDate: String? = null,
)
