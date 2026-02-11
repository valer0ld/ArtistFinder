package com.valer0ld.artistfinder.ui.models

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Immutable
data class TextConfig (
    val text: String,
    val color: Color,
    val style: TextStyle
)

object TextPreset {

    @Composable
    fun text(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface,
        style: TextStyle = MaterialTheme.typography.bodyMedium
    ) = TextConfig(
        text = text,
        color = color,
        style = style
    )

    @Composable
    fun body(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodyLarge
    )

    @Composable
    fun bodyM(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodyMedium
    )

    @Composable
    fun bodyS(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodySmall
    )

    @Composable
    fun titleS(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.titleSmall
    )

    @Composable
    fun title(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.titleMedium
    )

    @Composable
    fun titleL(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.titleLarge
    )

    @Composable
    fun display(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.displayMedium
    )

    @Composable
    fun displayS(
        text: String,
        color: Color = MaterialTheme.colorScheme.onSurface
    ) = TextConfig(
        text = text,
        color = color,
        style = MaterialTheme.typography.displaySmall
    )
}





