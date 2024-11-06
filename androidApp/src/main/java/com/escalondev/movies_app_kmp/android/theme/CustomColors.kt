package com.escalondev.movies_app_kmp.android.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Holds the custom colors not available in the material3 colorScheme for the app to be provided
 * by composition local to avoid accessing them directly from the Color.kt file
 */
data class CustomColors(

    // Gradients
    val primaryGradient: List<Color> = listOf(
        md_theme_light_primary_gradient_1,
        md_theme_light_primary_gradient_2,
        md_theme_light_primary_gradient_3,
    ),
)

val LocalCustomColors = compositionLocalOf { CustomColors() }

val MaterialTheme.customColors: CustomColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColors.current
