package com.valer0ld.artistfinder.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle

fun TextStyle.toSpanStyleColored(
    color: Color? = null,
    background: Color? = null
): SpanStyle {
    return SpanStyle(
        color = color ?: this.color,
        fontSize = this.fontSize,
        fontWeight = this.fontWeight,
        fontStyle = this.fontStyle,
        fontSynthesis = this.fontSynthesis,
        fontFamily = this.fontFamily,
        fontFeatureSettings = this.fontFeatureSettings,
        letterSpacing = this.letterSpacing,
        baselineShift = this.baselineShift,
        textGeometricTransform = this.textGeometricTransform,
        localeList = this.localeList,
        background = background ?: Color.Transparent,
        textDecoration = this.textDecoration,
        shadow = this.shadow
    )
}