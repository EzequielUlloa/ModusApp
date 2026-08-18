package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.AvatarStyle
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Fila de lista genérica
 *
 * Tarjeta con avatar (iniciales o ícono) + título + subtítulo, repetida en
 * los cursos del Home y en las opciones de la pantalla de Perfil. Pensada
 * para que el Frente 3 la reutilice en Historial y Elegir curso.
 *
 * @param avatarLabel iniciales mostradas en el avatar (ignorado si se pasa [avatarIcon]).
 * @param avatarIcon ícono mostrado en el avatar en vez de iniciales.
 * @param trailing contenido opcional al final de la fila (ej. [CourseProgressRing]).
 */
@Composable
fun ListRowCard(
    title: String,
    subtitle: String,
    avatarStyle: AvatarStyle,
    modifier: Modifier = Modifier,
    avatarLabel: String? = null,
    avatarIcon: ImageVector? = null,
    onClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(ModusSurface)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(avatarStyle.gradient),
            contentAlignment = Alignment.Center
        ) {
            if (avatarIcon != null) {
                Icon(imageVector = avatarIcon, contentDescription = null, tint = avatarStyle.onColor)
            } else if (avatarLabel != null) {
                Text(text = avatarLabel, color = avatarStyle.onColor, fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(text = title, color = ModusText, fontSize = 14.5.sp, fontWeight = FontWeight.Medium)
            Text(text = subtitle, color = ModusTextMuted, fontSize = 12.5.sp)
        }
        trailing?.invoke()
    }
}
