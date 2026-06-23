package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = ScholarlyDarkPrimary,
    secondary = ScholarlyDarkSecondary,
    tertiary = ScholarlyDarkTertiary,
    background = ScholarlyDarkBg,
    surface = ScholarlyDarkSurface,
    onPrimary = ScholarlyDarkBg,
    onSecondary = ScholarlyDarkBg,
    onTertiary = ScholarlyDarkBg,
    onBackground = ScholarlyLightBg,
    onSurface = ScholarlyLightBg
)

private val LightColorScheme = lightColorScheme(
    primary = ScholarlyLightPrimary,
    secondary = ScholarlyLightSecondary,
    tertiary = ScholarlyLightTertiary,
    background = ScholarlyLightBg,
    surface = ScholarlyLightSurface,
    onPrimary = ScholarlyLightSurface,
    onSecondary = ScholarlyLightSurface,
    onTertiary = ScholarlyLightSurface,
    onBackground = ScholarlyDarkBg,
    onSurface = ScholarlyDarkBg
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Set to false by default to showcase our gorgeous brand identity
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
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
