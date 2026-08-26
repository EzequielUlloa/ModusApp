package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusDanger
import com.una.modus.ui.theme.ModusDangerSoft
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Estado de error
 *
 * Ícono de alerta en círculo + título + subtítulo + botón de reintentar
 * (y una acción secundaria opcional), centrados en la pantalla. Reutilizado
 * cuando falla la carga de datos de un caso de uso (cursos, historial),
 * siguiendo el mismo lenguaje visual que "25 · Sin conexión" en Figma.
 *
 * @param title título corto del error.
 * @param subtitle explicación breve y, si aplica, qué se puede hacer mientras tanto.
 * @param retryText texto del botón principal (normalmente "Reintentar").
 * @param onRetry callback invocado al presionar el botón principal.
 * @param secondaryActionText texto de una acción secundaria opcional (ej. "Ver guardados").
 * @param onSecondaryAction callback de la acción secundaria; si es `null` no se muestra.
 */
@Composable
fun ErrorStateView(
    title: String,
    subtitle: String,
    retryText: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryActionText: String? = null,
    onSecondaryAction: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(ModusDangerSoft)
                .padding(26.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Filled.PriorityHigh, contentDescription = null, tint = ModusDanger, modifier = Modifier.size(32.dp))
        }
        Text(
            text = title,
            color = ModusText,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle,
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            textAlign = TextAlign.Center
        )
        PrimaryGradientButton(text = retryText, onClick = onRetry, modifier = Modifier.padding(top = 4.dp))
        if (secondaryActionText != null && onSecondaryAction != null) {
            SecondaryButton(text = secondaryActionText, onClick = onSecondaryAction)
        }
    }
}
