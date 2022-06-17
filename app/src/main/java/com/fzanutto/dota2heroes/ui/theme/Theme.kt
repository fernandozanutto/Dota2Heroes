package com.fzanutto.dota2heroes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = BackgroundDarkGray,
    onPrimary = Color.White,
    surface = Red,
    onSurface = Color.White,
)

private val LightColorPalette = lightColorScheme(
    primary = Color.White,
    onPrimary = Red,
    surface = Red,
    onSurface = Color.White,
)

@Composable
fun AppTheme(darkTheme: Boolean = true /*isSystemInDarkTheme()*/, content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}
