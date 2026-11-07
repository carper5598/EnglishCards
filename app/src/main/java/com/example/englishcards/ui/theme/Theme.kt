package com.example.englishcards.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple = Color(0xFF6A1B9A)
val PurpleLight = Color(0xFF9C27B0)
val Turquoise = Color(0xFF26C6DA)
val White = Color(0xFFFFFFFF)
val BgLight = Color(0xFFF7F2FB)

private val LightColors = lightColorScheme(
    primary = Purple,
    onPrimary = White,
    secondary = Turquoise,
    onSecondary = White,
    background = BgLight,
    onBackground = Color(0xFF1F1B24),
    surface = White,
    onSurface = Color(0xFF1F1B24)
)

@Composable
fun EnglishCardsTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColors, content = content)
}
