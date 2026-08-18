package com.una.modus.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BottomNavDestination
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.ListRowCard
import com.una.modus.presentation.common.components.ModusBottomNavBar
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAvatarBlue
import com.una.modus.ui.theme.ModusAvatarOrange
import com.una.modus.ui.theme.ModusAvatarPurple
import com.una.modus.ui.theme.ModusAvatarTeal
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusButtonGradient
import com.una.modus.ui.theme.ModusOnPrimary
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Perfil y ajustes
 *
 * Menú de opciones del usuario (sin drawer lateral, ya que el prototipo de
 * Figma no define uno): editar perfil, notificaciones, privacidad, ayuda y
 * cerrar sesión. Es el destino de la pestaña "Perfil" de la barra inferior.
 */
@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier,
    userName: String = "Josué Ulloa",
    userEmail: String = "josue.ulloa@est.una.ac.cr",
    onBack: () -> Unit = {},
    onEditProfile: () -> Unit = {},
    onNotifications: () -> Unit = {},
    onPrivacy: () -> Unit = {},
    onHelp: () -> Unit = {},
    onLogout: () -> Unit = {},
    onNavigateInicio: () -> Unit = {},
    onNavigateCursos: () -> Unit = {},
    onNavigateAvisos: () -> Unit = {},
    onCapturar: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp)
                .padding(top = 24.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            BackTopButton(onClick = onBack)

            Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(ModusButtonGradient),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = initialsOf(userName), color = ModusOnPrimary, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                }
                Column {
                    Text(text = userName, color = ModusText, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = userEmail, color = ModusTextMuted, fontSize = 12.5.sp)
                }
            }

            Text(
                text = "CUENTA",
                color = ModusTextMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.1.sp
            )

            ListRowCard(
                title = "Editar perfil",
                subtitle = "Nombre, foto y carrera",
                avatarStyle = ModusAvatarBlue,
                avatarIcon = Icons.Filled.Edit,
                onClick = onEditProfile
            )
            ListRowCard(
                title = "Notificaciones",
                subtitle = "Avisos de análisis listos",
                avatarStyle = ModusAvatarTeal,
                avatarIcon = Icons.Filled.Notifications,
                onClick = onNotifications
            )
            ListRowCard(
                title = "Privacidad",
                subtitle = "Qué se guarda de tus apuntes",
                avatarStyle = ModusAvatarPurple,
                avatarIcon = Icons.Filled.Lock,
                onClick = onPrivacy
            )
            ListRowCard(
                title = "Ayuda",
                subtitle = "Preguntas frecuentes",
                avatarStyle = ModusAvatarOrange,
                avatarIcon = Icons.AutoMirrored.Filled.Help,
                onClick = onHelp
            )

            PrimaryGradientButton(text = "Cerrar sesión", onClick = onLogout)
        }

        ModusBottomNavBar(
            current = BottomNavDestination.PERFIL,
            onSelect = { destination ->
                when (destination) {
                    BottomNavDestination.INICIO -> onNavigateInicio()
                    BottomNavDestination.CURSOS -> onNavigateCursos()
                    BottomNavDestination.AVISOS -> onNavigateAvisos()
                    BottomNavDestination.PERFIL -> Unit
                }
            },
            onCapturar = onCapturar,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

private fun initialsOf(name: String): String =
    name.trim()
        .split(" ")
        .filter { it.isNotEmpty() }
        .take(2)
        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
        .joinToString("")
