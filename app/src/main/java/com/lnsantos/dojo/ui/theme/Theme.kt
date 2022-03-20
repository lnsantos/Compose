package com.lnsantos.dojo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BackgroundItemNameNight,
    primaryVariant = BackgroundScreenNight,
    secondary = BackgroundButtonNight,
    background = TextColorItemIndicatorNight
)

private val LightColorPalette = lightColors(
    surface = BackgroundItemName,
    onSurface = BackgroundScreen,
    primary = BackgroundButton,
    onPrimary = TextColorItemIndicator
)

@Composable
fun DojoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}