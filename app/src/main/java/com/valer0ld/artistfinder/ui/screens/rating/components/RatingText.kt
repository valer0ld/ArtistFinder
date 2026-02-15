package com.valer0ld.artistfinder.ui.screens.rating.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun RatingText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.tertiary
){
    Text(
        text = text,
        style = style,
        color = color,
        maxLines = 1,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RatingHeaderText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer
){
    Text(
        text = text,
        style = style,
        color = color,
        maxLines = 2,
        modifier = modifier,
        overflow = TextOverflow.Visible,
        textAlign = TextAlign.Center
    )
}
