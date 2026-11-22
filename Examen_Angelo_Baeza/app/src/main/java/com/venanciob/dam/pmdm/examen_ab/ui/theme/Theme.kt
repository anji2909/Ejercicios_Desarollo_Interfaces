package com.venanciob.dam.pmdm.examen_ab.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = AnjiBrown,
    onPrimary = White,
    primaryContainer = AnjiMint, // El menta funciona genial como fondo de contenedores
    onPrimaryContainer = AnjiBrown,

    secondary = AnjiPink,
    onSecondary = AnjiBrown,
    secondaryContainer = AnjiPink.copy(alpha = 0.2f), // Un rosa más suave para fondos
    onSecondaryContainer = AnjiBrown,

    background = White,
    onBackground = AnjiGrayDark,

    surface = White,
    onSurface = AnjiGrayDark,
    surfaceVariant = AnjiGrayLight,
    onSurfaceVariant = AnjiGrayDark,

    outline = AnjiGrayMedium
)

private val DarkColorScheme = darkColorScheme(
    // Para el modo oscuro, invertimos un poco las prioridades para no deslumbrar
    primary = AnjiPink,
    onPrimary = AnjiBrown,
    primaryContainer = AnjiBrown,
    onPrimaryContainer = AnjiPink,

    secondary = AnjiMint,
    onSecondary = AnjiGrayDark,

    background = AnjiGrayDark,
    onBackground = AnjiGrayLight,

    surface = AnjiGrayDark,
    onSurface = AnjiGrayLight,
    surfaceVariant = AnjiGrayMedium,
    onSurfaceVariant = White,

    outline = AnjiGrayMedium
)

@Composable
fun AnjiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Para forzar SIEMPRE los colores de tu marca, apagamos el color dinámico por defecto
    dynamicColor: Boolean = false,
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asegúrate de tener tu Type.kt configurado
        content = content
    )
}