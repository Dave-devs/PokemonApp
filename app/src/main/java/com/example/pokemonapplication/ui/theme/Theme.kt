package com.example.pokemonapplication.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue30,
    primaryVariant = Gray30,
    secondary = Blue80,
    secondaryVariant = Blue20,
    background = Gray10,
    surface = Gray40,
    error = Error1,
    onPrimary = Blue80,
    onSecondary = Gray90,
    onBackground = Gray90,
    onSurface = Gray90,
    onError = Color.White
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Blue80,
    primaryVariant = Gray90,
    secondary = Blue90,
    secondaryVariant = Gray10,
    background = DarkBlue90,
    surface = Blue90,
    error = Error2,
    onPrimary = DarkBlue10,
    onSecondary = Black10,
    onBackground = Black10,
    onSurface = Black90,
    onError = Color.White
)

@Composable
fun PokemonApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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