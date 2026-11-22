package com.venanciob.dam.pmdm.apppro_examen_parte3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.ui.graphics.Color

// Colores principales de la marca Anji
val AnjiBrown = Color(0xFF754423)
val AnjiPink = Color(0xFFFF99C7)
val AnjiMint = Color(0xFFB1EFE3)

// Escala de grises de la marca Anji
val AnjiGrayDark = Color(0xFF3C3C3B)
val AnjiGrayMedium = Color(0xFFA8A8A7)
val AnjiGrayLight = Color(0xFFE3E3E3)


// Colores adicionales necesarios para buen contraste

val White = Color(0xFFFFFFFF)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)