package com.escalondev.movies_app_kmp.android.ui.filter

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.component.CircleIconComponent
import com.escalondev.movies_app_kmp.android.util.Constants.HALF_SEC
import com.escalondev.movies_app_kmp.android.util.Constants.MILLISECONDS_200
import com.escalondev.movies_app_kmp.android.util.Constants.NINETY_DEGREES
import com.escalondev.movies_app_kmp.android.util.Constants.ONE_HUNDRED
import com.escalondev.movies_app_kmp.android.util.Constants.TWO_HUNDRED
import com.escalondev.movies_app_kmp.android.util.Constants.ZERO_DEGREES
import com.escalondev.movies_app_kmp.android.util.calculateAlphaForItem
import com.escalondev.movies_app_kmp.android.util.getGradientBackgroundMask
import com.escalondev.movies_app_kmp.android.util.getOptionTextStyle
import com.escalondev.movies_app_kmp.android.util.getScreenHeight
import com.escalondev.movies_app_kmp.android.util.toPx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * This Dialog screen shows a list of given string options to allow the selection of one item.
 * It returns the selected value to the caller screen.
 */
@Composable
fun SelectOptionsScreen(
    options: List<String>,
    selectedOption: String,
    onSelectedOption: (String) -> Unit,
    onDismiss: () -> Unit,
) {

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        SelectOptionContent(
            options = options,
            selectedOption = selectedOption,
            onSelectedOption = onSelectedOption,
            onDismiss = onDismiss
        )
    }
}

@Composable
fun SelectOptionContent(
    options: List<String>,
    selectedOption: String,
    onSelectedOption: (String) -> Unit,
    onDismiss: () -> Unit,
) {

    val dismissIconRotation = remember { Animatable(ZERO_DEGREES) }
    val coroutineScope = rememberCoroutineScope()

    // Execute icon animation right after opening the screen.
    LaunchedEffect(key1 = Unit) {
        handleIconRotationAnimation(
            dismissIconRotation = dismissIconRotation,
            coroutineScope = coroutineScope,
            degrees = -NINETY_DEGREES
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(colors = getGradientBackgroundMask())
            )
        ) {

            SortOptionsList(
                options = options,
                selectedOption = selectedOption,
                onSelectedOption = {
                    handleIconRotationAnimation(
                        dismissIconRotation = dismissIconRotation,
                        coroutineScope = coroutineScope,
                        onDismiss = onDismiss
                    )
                    onSelectedOption(it)
                }
            )

            CircleIconComponent(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                dismissIconRotation = dismissIconRotation.value,
                onClick = {
                    handleIconRotationAnimation(
                        dismissIconRotation = dismissIconRotation,
                        coroutineScope = coroutineScope,
                        onDismiss = onDismiss
                    )
                }
            )
        }
    }
}

/**
 * Composable responsible to draw the list of watchlist items within a LazyColum.
 */
@Composable
fun SortOptionsList(
    options: List<String>,
    selectedOption: String,
    onSelectedOption: (String) -> Unit,
) {

    // Animate scrolling to selected the item after
    // half second of delay once the screen loads.
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        delay(HALF_SEC)
        listState.animateScrollToItem(index = options.indexOf(selectedOption))
    }

    val bottomAlphaThreshold = getScreenHeight().minus(TWO_HUNDRED.dp.toPx())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 100.dp),
        state = listState
    ) {
        itemsIndexed(options) { index, option ->

            // Calculate the alpha opacity based on the items position.
            // This will add a less opaque effect on the items when reaching
            // the top and bottom of the screen.
            val alpha = calculateAlphaForItem(
                listState = listState,
                index = index,
                topThreshold = ONE_HUNDRED,
                bottomThreshold = bottomAlphaThreshold
            )

            SelectOptionItem(
                modifier = Modifier.graphicsLayer {
                    // Display the alpha opacity at the top and bottom
                    // based on the thresholds.
                    this.alpha = alpha
                },
                style = getOptionTextStyle(isSelected = option == selectedOption),
                option = option,
                onSelectedOption = { onSelectedOption(option) }
            )
        }
    }
}

/**
 * Select option composable item.
 */
@Composable
fun SelectOptionItem(
    modifier: Modifier = Modifier,
    style: TextStyle,
    option: String,
    onSelectedOption: () -> Unit
) {
    Card(
        modifier = modifier.padding(vertical = 28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(
            modifier = Modifier
                .clickable { onSelectedOption() }
                .padding(horizontal = 16.dp),
            style = style,
            text = option
        )
    }
}

/**
 * Handle the animation rotation logic for the cross icon from the bottom.
 */
fun handleIconRotationAnimation(
    dismissIconRotation: Animatable<Float, *>,
    coroutineScope: CoroutineScope,
    degrees: Float = ZERO_DEGREES,
    onDismiss: () -> Unit = { }
) {
    coroutineScope.launch {
        dismissIconRotation.animateTo(degrees)
        delay(MILLISECONDS_200)
        onDismiss()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SelectOptionsScreenPreview() {
    MoviesAppTheme {
        SelectOptionsScreen(
            options = SortType.entries.map { it.name },
            selectedOption = "",
            onSelectedOption = {},
            onDismiss = {}
        )
    }
}
