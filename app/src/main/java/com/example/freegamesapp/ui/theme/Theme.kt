package com.example.freegamesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Light_Soft,
    secondary = Dark_Light,
    background = Dark_Heavy,
    surface = Dark_Max,
    onSurface = Dark_Medium,
    primaryVariant = Light_Heavy
)

private val LightColorPalette = lightColors(
    primary = Dark_Medium,
    secondary = Dark_Light,
    background = Light_Max,
    surface = Light_Medium,
    onSurface = Light_Soft,
    primaryVariant = Dark_Heavy
)

@Composable
fun FreeGamesAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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