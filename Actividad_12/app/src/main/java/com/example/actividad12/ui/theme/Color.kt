package com.example.actividad12.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val primary = Color(0xFF673AB7)
val secondary = Color(0xFF3BB2E1)
val surface = Color(0xFF84C773)

val FondoClaro = Color(0xFF3AD0A7)

val VerdeOscuro = Color(0xFF1E3A1F)
val FondoOscuro = Color(0xFF101816)

val LightColors = lightColorScheme(
    primary = primary,
    onPrimary = Color.White,
    primaryContainer = secondary,
    secondary = surface,
    background = FondoClaro,
    surface = Color.White
)

val DarkColors = darkColorScheme(
    primary = VerdeOscuro,
    onPrimary = Color.Black,
    background = FondoOscuro,
    surface = Color(0xFF18211F)
)