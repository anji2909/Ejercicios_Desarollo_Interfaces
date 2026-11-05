package com.example.actividad12.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Asegúrate de que las variables 'primary', 'secondary', y 'surface'
// estén correctamente definidas en tu archivo Color.kt
private val MisColoresOscuros = darkColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = surface
)

private val MisColoresClaros = lightColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = surface
)

@Composable
fun MiTema(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Asignamos la paleta de colores correcta dependiendo del estado
    val colors = if (darkTheme) {
        MisColoresOscuros
    } else {
        MisColoresClaros
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography, // Añadimos la tipografía
        content = content
    )
}