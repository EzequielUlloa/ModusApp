package com.una.modus.presentation.common.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusAccentGradient
import com.una.modus.ui.theme.ModusProgressTrack
import com.una.modus.ui.theme.ModusText

/**
 * Anillo de progreso de curso
 *
 * Círculo de progreso (pista + arco verde) con el porcentaje al centro,
 * usado en las tarjetas de curso del Home. A diferencia del diseño de
 * Figma (que exporta un SVG distinto por cada porcentaje fijo), este se
 * dibuja con [Canvas] para aceptar cualquier valor de [progress].
 *
 * @param progress porcentaje de avance, 0..100.
 */
@Composable
fun CourseProgressRing(progress: Int, modifier: Modifier = Modifier) {
    // Se leen acá (en contexto @Composable) porque el bloque de Canvas se
    // ejecuta en la fase de dibujo, donde no se puede leer un color/brush
    // que dependa del tema (@Composable) directamente.
    val trackColor = ModusProgressTrack
    val progressBrush = ModusAccentGradient
    Box(modifier = modifier.size(44.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            drawArc(
                color = trackColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = stroke
            )
            drawArc(
                brush = progressBrush,
                startAngle = -90f,
                sweepAngle = 360f * (progress.coerceIn(0, 100) / 100f),
                useCenter = false,
                style = stroke
            )
        }
        Text(
            text = "$progress",
            color = ModusText,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
