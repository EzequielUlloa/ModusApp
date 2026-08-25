package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusPrimaryText
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Estado vacío
 *
 * Ícono en círculo + título + subtítulo + acción opcional, centrados en
 * la pantalla. Reutilizado por Cursos, "Elegir curso" e Historial cuando
 * todavía no hay cursos o apuntes que mostrar (pantallas "22 · Sin
 * cursos" y "23 · Curso sin apuntes" del prototipo de Figma).
 *
 * @param icon ícono mostrado dentro del círculo superior.
 * @param title título corto y directo del estado vacío.
 * @param subtitle explicación breve de qué hacer a continuación.
 * @param actionText texto del botón de acción; si es `null` no se muestra botón.
 * @param onAction callback invocado al presionar el botón de acción.
 */
@Composable
fun EmptyStateView(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(ModusSurface)
                .padding(26.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = ModusPrimaryText, modifier = Modifier.size(32.dp))
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
        if (actionText != null && onAction != null) {
            PrimaryGradientButton(text = actionText, onClick = onAction, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
