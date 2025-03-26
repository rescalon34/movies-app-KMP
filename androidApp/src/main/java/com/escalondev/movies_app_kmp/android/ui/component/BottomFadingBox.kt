package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.escalondev.movies_app_kmp.android.util.addBottomBackgroundBrush

@Composable
fun BottomFadingBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                brush = addBottomBackgroundBrush(),
                shape = MaterialTheme.shapes.extraSmall
            )
    ) {
        content()
    }
}
