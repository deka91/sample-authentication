package ch.dk.sampleauthentication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Gray,
    onPrimary = White,
    primaryContainer = LightGreen,
    onPrimaryContainer = White,
    inversePrimary = White,
    secondary = Green,
    onSecondary = White,
    secondaryContainer = DarkGreen,
    onSecondaryContainer = White,
    tertiary = LightBlue,
    onTertiary = White,
    tertiaryContainer = Light2,
    onTertiaryContainer = Black,
    error = Red,
    background = DarkGray,
    onBackground = White,
    surface = Light2,
    onSurface = Black,
    inverseSurface = LightGreen,
    inverseOnSurface = Black,
    surfaceVariant = Green,
    onSurfaceVariant = White,
    outline = Light2,
    outlineVariant = Gray
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = Gray,
    onPrimaryContainer = White,
    inversePrimary = White,
    secondary = Green,
    onSecondary = White,
    secondaryContainer = DarkGreen,
    onSecondaryContainer = White,
    tertiary = LightBlue,
    onTertiary = White,
    tertiaryContainer = Light2,
    onTertiaryContainer = Black,
    error = Red,
    background = White,
    onBackground = Black,
    surface = Light2,
    onSurface = Black,
    inverseSurface = LightGreen,
    inverseOnSurface = Black,
    surfaceVariant = Light2,
    onSurfaceVariant = Gray,
    outline = Light2,
    outlineVariant = Gray
)

@Composable
fun SampleAuthenticationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}