package com.example.emiyo.core.design

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val WardrobeColorScheme = lightColorScheme(
    primary = WardrobeOlive,
    onPrimary = WardrobeWhite,
    secondary = WardrobeCharcoalMuted,
    onSecondary = WardrobeWhite,
    tertiary = WardrobeTerracotta,
    background = WardrobeSurface,
    onBackground = WardrobeCharcoal,
    surface = WardrobeIvory,
    onSurface = WardrobeCharcoal
)

@Composable
fun WardrobeTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                // Configure status bar icons for light editorial background
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = WardrobeColorScheme,
        typography = WardrobeMaterialTypography,
        content = content
    )
}
