package com.escalondev.domain.model.movie

data class Movie(
    val id: Int,
    val title: String?,
    val imageUrl: String?,
    val releaseDate: String? = null,
)
