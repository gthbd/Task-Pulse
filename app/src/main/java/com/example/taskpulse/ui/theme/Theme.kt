package com.example.taskpulse.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
private val LightColors = lightColorScheme(
    primary = Pulse,
    background = Background,
    surface = Surface,
    onBackground = InkPrimary,
    onSurface = InkPrimary,
    secondary = InkSecondary,
    outline = Outline
)

private val DarkColors = darkColorScheme(
    primary = Pulse,
    background = BackgroundDark,
    surface = SurfaceDark,
    onBackground = InkPrimaryDark,
    onSurface = InkPrimaryDark,
    secondary = InkSecondaryDark,
    outline = OutlineDark
)

@Composable
fun TaskPulseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // sau này thay bằng giá trị đọc từ DataStore
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = TaskPulseTypography,
        content = content
    )
}