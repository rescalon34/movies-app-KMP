package com.escalondev.movies_app_kmp.android.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escalondev.movies_app_kmp.android.theme.MoviesAppTheme

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: String,
    onCategoryClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onCategoryClick,
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)) {
            Text(text = category)
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = category
            )
        }
    }
}

@Preview
@Composable
private fun CategoryCardPreview() {
    MoviesAppTheme {
        CategoryCard(
            category = "Featured",
            onCategoryClick = {}
        )
    }
}
