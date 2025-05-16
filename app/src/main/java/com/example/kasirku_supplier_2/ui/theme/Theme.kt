package com.example.kasirku_supplier_2.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val DarkBackground = Color(0xFF1C1C1C)
val DarkCard = Color(0xFF2A2A2A)
val AccentGreen = Color(0xFF00C853)
val AccentText = Color.White
val BorderGray = Color(0xFF424242)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun KasirkuTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(),
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}
