package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusButtonGradient
import com.una.modus.ui.theme.ModusOnPrimary
import com.una.modus.ui.theme.ModusPrimaryText
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusTextMuted

/** Destinos de la barra de navegación inferior. */
enum class BottomNavDestination { INICIO, CURSOS, AVISOS, PERFIL }

/**
 * Barra de navegación inferior
 *
 * Barra fija con 4 pestañas (Inicio, Cursos, Avisos, Perfil) y un botón
 * flotante central de cámara ("Capturar") que se superpone a la barra,
 * tal como en el prototipo de Figma. Se usa en todas las pantallas
 * principales (Home, Cursos, Avisos, Perfil).
 *
 * @param current pestaña actualmente resaltada.
 * @param onSelect callback invocado con el destino elegido al tocar una pestaña.
 * @param onCapturar callback invocado al tocar el botón flotante de cámara.
 */
@Composable
fun ModusBottomNavBar(
    current: BottomNavDestination,
    onSelect: (BottomNavDestination) -> Unit,
    onCapturar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(100.dp))
                .background(ModusSurface)
                .padding(vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = Icons.Filled.Home,
                label = "Inicio",
                selected = current == BottomNavDestination.INICIO,
                modifier = Modifier.weight(1f)
            ) { onSelect(BottomNavDestination.INICIO) }
            NavItem(
                icon = Icons.AutoMirrored.Filled.MenuBook,
                label = "Cursos",
                selected = current == BottomNavDestination.CURSOS,
                modifier = Modifier.weight(1f)
            ) { onSelect(BottomNavDestination.CURSOS) }
            Spacer(modifier = Modifier.weight(1f))
            NavItem(
                icon = Icons.Filled.Notifications,
                label = "Avisos",
                selected = current == BottomNavDestination.AVISOS,
                modifier = Modifier.weight(1f)
            ) { onSelect(BottomNavDestination.AVISOS) }
            NavItem(
                icon = Icons.Filled.Person,
                label = "Perfil",
                selected = current == BottomNavDestination.PERFIL,
                modifier = Modifier.weight(1f)
            ) { onSelect(BottomNavDestination.PERFIL) }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-18).dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(ModusButtonGradient)
                .clickable(onClick = onCapturar),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Filled.PhotoCamera, contentDescription = "Capturar apunte", tint = ModusOnPrimary)
        }
    }
}

@Composable
private fun NavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val color = if (selected) ModusPrimaryText else ModusTextMuted
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(if (selected) ModusPrimaryText.copy(alpha = 0.16f) else Color.Transparent)
                .indication(interactionSource, ripple(bounded = true, radius = 17.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = color, modifier = Modifier.size(20.dp))
        }
        Text(
            text = label,
            color = color,
            fontSize = 10.5.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}
