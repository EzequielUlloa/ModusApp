package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusAccentGradient
import com.una.modus.ui.theme.ModusOnAccent

/**
 * Insignia (badge) en píldora
 *
 * Etiqueta pequeña con el gradiente de acento verde, usada como elemento
 * final de una fila de lista para marcar un estado corto (ej. "Nuevo" en
 * Historial, "Listo"/"En curso" durante un análisis).
 */
@Composable
fun ModusBadge(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(ModusAccentGradient)
            .padding(horizontal = 11.dp, vertical = 6.dp)
    ) {
        Text(text = text, color = ModusOnAccent, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}
