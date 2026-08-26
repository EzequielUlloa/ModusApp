package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusButtonGradient
import com.una.modus.ui.theme.ModusOnPrimary
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Chip de filtro
 *
 * Píldora seleccionable usada en filas de filtros (ej. "Todos" / "Este
 * mes" / "Favoritos" en Historial). El seleccionado toma el gradiente de
 * botón primario; el resto queda en superficie con texto apagado.
 *
 * @param selected si este chip es el filtro activo.
 * @param onClick callback invocado al tocar el chip.
 */
@Composable
fun ModusFilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = if (selected) ModusButtonGradient else Brush.linearGradient(listOf(ModusSurface, ModusSurface))
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 15.dp, vertical = 9.dp)
    ) {
        Text(
            text = text,
            color = if (selected) ModusOnPrimary else ModusTextMuted,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
        )
    }
}
