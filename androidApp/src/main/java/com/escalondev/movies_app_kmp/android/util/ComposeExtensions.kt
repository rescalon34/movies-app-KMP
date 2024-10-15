package com.escalondev.movies_app_kmp.android.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Convert Dp to pixels
 */
@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}

@Composable
fun getScreenHeight(): Float {
    return LocalConfiguration.current.screenHeightDp.dp.toPx()
}

/**
 * Dynamically calculates the alpha based on the position of the item in the list.
 */
@Composable
fun calculateAlphaForItem(
    listState: LazyListState,
    index: Int,
    topThreshold: Float,
    bottomThreshold: Float
): Float {

    val itemInfo by remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.firstOrNull {
                it.index == index
            }
        }
    }

    // If the item is visible, calculate its alpha based on its position
    return itemInfo?.let { item ->
        val itemCenterY = (item.offset + item.size)
        when {
            // If the item is in the top or bottom threshold, make it less opaque
            itemCenterY < topThreshold || itemCenterY > bottomThreshold -> 0.5f
            // Otherwise, make it fully opaque
            else -> 1f
        }
    } ?: 1f
}

/**
 * Extracted composable function to get the style depending on the selected option.
 */
@Composable
fun getOptionTextStyle(isSelected: Boolean) = if (isSelected) {
    MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold
    )
} else {
    MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
    )
}

/**
 * Modifier function to detect whenever the composable item is being pressed or not.
 */
fun Modifier.detectOnPress(isPressing: (Boolean) -> Unit) = pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            val isPressed = event.changes.any { it.pressed }
            isPressing(isPressed)
        }
    }
}
