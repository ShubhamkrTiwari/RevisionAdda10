package com.shubhamkrtiwari.revisionadda.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color(0xFF6A4AE2),
    primaryVariant = Color(0xFF4A2BCC),
    secondary = Color(0xFF03A9F4),
    background = Color(0xFFF5F7FB),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onBackground = Color(0xFF1F2A44),
    onSurface = Color(0xFF1F2A44)
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFF8F7BFF),
    primaryVariant = Color(0xFF6A4AE2),
    secondary = Color(0xFF82B1FF),
    background = Color(0xFF0E0F14),
    surface = Color(0xFF121217),
    onPrimary = Color.White,
    onBackground = Color(0xFFECEFF4),
    onSurface = Color(0xFFECEFF4)
)

@Composable
fun RevisionAddaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = androidx.compose.material.Typography(), // you can replace with custom typography
        shapes = androidx.compose.material.Shapes(),
        content = content
    )
}
