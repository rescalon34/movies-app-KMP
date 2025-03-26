package com.escalondev.movies_app_kmp.android.util

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.os.ConfigurationCompat
import com.escalondev.movies_app_kmp.android.util.Constants.YOUTUBE_EMBED_URL

fun getGradientBackgroundMask(): List<Color> {
    return listOf(
        Color.Black.copy(alpha = 0.5f),
        Color.Transparent,
        Color.Black.copy(alpha = 0.5f),
        Color.Black.copy(alpha = 0.8f),
    )
}

fun addBottomBackgroundBrush(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black.copy(alpha = 0.3f),
            Color.Black.copy(alpha = 0.5f),
            Color.Black.copy(alpha = 0.8f),
        ),
        startY = 0f,
        endY = 180f
    )
}

fun getCurrentLanguageCode(): String {
    val languageTag = ConfigurationCompat.getLocales(
        Resources.getSystem().configuration
    ).get(0)?.toLanguageTag() ?: DEFAULT_LANGUAGE_TAG
    return languageTag
}

fun getVideoUrl(videoKey: String): String {
    return "$YOUTUBE_EMBED_URL$videoKey"
}

fun Context.shareMovieDetails(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

const val DEFAULT_LANGUAGE_TAG = "en-US"
