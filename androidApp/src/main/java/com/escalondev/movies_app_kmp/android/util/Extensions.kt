package com.escalondev.movies_app_kmp.android.util

import androidx.compose.ui.graphics.Color

fun getGradientBackgroundMask(): List<Color> {
    return listOf(
        Color.Transparent,
        Color.Transparent,
        Color.Transparent,
        Color.Black.copy(alpha = 0.5f),
        Color.Black.copy(alpha = 0.8f),
    )
}
