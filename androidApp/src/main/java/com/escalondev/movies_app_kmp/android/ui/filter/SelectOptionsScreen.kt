package com.escalondev.movies_app_kmp.android.ui.filter

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme
import com.escalondev.movies_app_kmp.android.ui.component.CircleIconComponent
import com.escalondev.movies_app_kmp.android.util.Constants.HALF_SEC
import com.escalondev.movies_app_kmp.android.util.Constants.MILLISECONDS_200
import com.escalondev.movies_app_kmp.android.util.Constants.NINETY_DEGREES
import com.escalondev.movies_app_kmp.android.util.getGradientBackgroundMask
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

    val dismissIconRotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

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
                    handleRotationAndDismiss(
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
                    handleRotationAndDismiss(
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

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        items(options) { option ->
            val style = if (option == selectedOption)
                MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold) else {
                MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }

            SelectOptionItem(
                style = style,
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
 * Reusable function to handle the animation rotation logic whenever dismissing the dialog.
 */
fun handleRotationAndDismiss(
    dismissIconRotation: Animatable<Float, *>,
    coroutineScope: CoroutineScope,
    onDismiss: () -> Unit
) {
    coroutineScope.launch {
        dismissIconRotation.animateTo(NINETY_DEGREES)
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
