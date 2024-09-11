package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.util.Constants.HALF_SEC

/**
 * Reusable Dismiss icon button around a circular shape.
 */
@Composable
fun CircleIconComponent(
    modifier: Modifier = Modifier,
    dismissIconRotation: Float = 0f,
    onClick: () -> Unit,
) {

    // handle the duration of the animation by half second.
    val animatedRotation by animateFloatAsState(
        targetValue = dismissIconRotation,
        animationSpec = tween(
            durationMillis = HALF_SEC.toInt(),
            easing = LinearOutSlowInEasing
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .then(modifier)
            .size(60.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .rotate(animatedRotation),
            imageVector = Icons.Default.Close,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun CircleIconComponentPreview() {
    MoviesAppTheme {
        CircleIconComponent(onClick = {})
    }
}
