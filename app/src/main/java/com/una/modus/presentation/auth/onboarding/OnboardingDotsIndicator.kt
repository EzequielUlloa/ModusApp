package com.una.modus.presentation.auth.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.una.modus.ui.theme.ModusLink
import com.una.modus.ui.theme.ModusProgressTrack

/**
 * Indicador de pasos del onboarding
 *
 * Fila de puntos que muestra en qué pantalla de bienvenida está el usuario
 * (siempre 3, una por cada pantalla del flujo). El punto activo se estira
 * en forma de píldora; los demás quedan como círculos pequeños.
 *
 * @param activeIndex índice (0 a 2) del punto activo/actual.
 */
@Composable
fun OnboardingDotsIndicator(activeIndex: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        repeat(3) { index ->
            val isActive = index == activeIndex
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(if (isActive) 22.dp else 8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (isActive) ModusLink else ModusProgressTrack)
            )
        }
    }
}

