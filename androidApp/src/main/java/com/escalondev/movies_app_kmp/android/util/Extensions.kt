package com.escalondev.movies_app_kmp.android.util

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import androidx.core.os.ConfigurationCompat

fun getGradientBackgroundMask(): List<Color> {
    return listOf(
        Color.Black.copy(alpha = 0.5f),
        Color.Transparent,
        Color.Black.copy(alpha = 0.5f),
        Color.Black.copy(alpha = 0.8f),
    )
}

fun getCurrentLanguageCode(): String {
    val languageTag = ConfigurationCompat.getLocales(
        Resources.getSystem().configuration
    ).get(0)?.toLanguageTag() ?: DEFAULT_LANGUAGE_TAG
    return languageTag
}

const val DEFAULT_LANGUAGE_TAG = "en-US"
