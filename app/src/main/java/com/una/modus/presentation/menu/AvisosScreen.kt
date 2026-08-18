package com.una.modus.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BottomNavDestination
import com.una.modus.presentation.common.components.ModusBottomNavBar
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Avisos
 *
 * Destino de la pestaña "Avisos" de la barra inferior. El PDF del Lab 2 no
 * detalla un diseño propio para esta pantalla (solo el ícono de la barra),
 * así que queda como estado vacío simple hasta que exista contenido real
 * (notificaciones de análisis completados, etc.).
 */
@Composable
fun AvisosScreen(
    modifier: Modifier = Modifier,
    onNavigateInicio: () -> Unit = {},
    onNavigateCursos: () -> Unit = {},
    onNavigatePerfil: () -> Unit = {},
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
                .padding(horizontal = 28.dp)
                .padding(top = 78.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Avisos", color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Todavía no tenés avisos. Acá vas a ver cuándo un análisis esté listo.",
                color = ModusTextMuted,
                fontSize = 12.5.sp
            )
        }

        ModusBottomNavBar(
            current = BottomNavDestination.AVISOS,
            onSelect = { destination ->
                when (destination) {
                    BottomNavDestination.INICIO -> onNavigateInicio()
                    BottomNavDestination.CURSOS -> onNavigateCursos()
                    BottomNavDestination.AVISOS -> Unit
                    BottomNavDestination.PERFIL -> onNavigatePerfil()
                }
            },
            onCapturar = onCapturar,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}
