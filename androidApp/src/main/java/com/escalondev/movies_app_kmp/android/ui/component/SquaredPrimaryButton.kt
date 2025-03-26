package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

@Composable
fun SquaredPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    iconVector: ImageVector? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ),
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 48.dp),
        enabled = enabled,
        colors = buttonColors,
        shape = shape,
        onClick = onClick
    ) {
        iconVector?.let {
            Icon(
                modifier = Modifier.padding(horizontal = 4.dp),
                imageVector = it,
                contentDescription = null
            )
        }
        Text(text = text)
    }
}

@Preview
@Composable
private fun SquaredPrimaryButtonPreview() {
    MoviesAppTheme {
        SquaredPrimaryButton(
            text = "Button",
            onClick = {}
        )
    }
}
