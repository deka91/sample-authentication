package ch.dk.sampleauthentication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}