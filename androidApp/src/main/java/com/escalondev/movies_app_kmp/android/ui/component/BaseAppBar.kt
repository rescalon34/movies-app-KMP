package com.escalondev.movies_app_kmp.android.ui.component

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.theme.customColors
import com.escalondev.movies_app_kmp.android.util.Constants.MID_ALPHA
import com.escalondev.movies_app_kmp.android.util.Constants.SHORT_ANIMATION_DURATION

/**
 * Reusable Top AppBar across different screens.
 *
 * @param modifier to apply any modification to the TopAppBar.
 * @param title the title of the AppBar
 * @param scrollBehavior the desired scrollBehavior whenever scrolling the screen content.
 * @param actions whether the AppBar must show extra action buttons to the right.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(
    modifier: Modifier = Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    isTransparent: Boolean = false,
    showNavigationIcon: Boolean = false,
    showNavigationContainerColor: Boolean = false,
    onBackButtonClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {

    val colorTarget = if (showNavigationContainerColor) {
        Color.Transparent.copy(alpha = MID_ALPHA)
    } else {
        Color.Transparent
    }

    val navContainerColor by animateColorAsState(
        targetValue = colorTarget,
        animationSpec = tween(
            durationMillis = SHORT_ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    val toolbarContainerColor by animateColorAsState(
        targetValue = if (isTransparent) Color.Transparent else
            MaterialTheme.colorScheme.surfaceContainer,
        label = ""
    )

    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = topAppBarColors(
            containerColor = toolbarContainerColor,
            titleContentColor = MaterialTheme.customColors.white,
        ),
        title = {
            Text(
                text = title,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(navContainerColor)
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable(
                                indication = null,
                                interactionSource = null,
                                onClick = onBackButtonClick
                            ),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BaseAppBarPreview() {
    MoviesAppTheme {
        BaseAppBar(
            title = "Home",
            showNavigationIcon = true
        )
    }
}
