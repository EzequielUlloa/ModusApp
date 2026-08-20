package com.una.modus.presentation.lists

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BottomNavDestination
import com.una.modus.presentation.common.components.ModusBottomNavBar
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Cursos
 *
 * Destino de la pestaña "Cursos" de la barra inferior. El listado completo
 * (con datos reales vía GetCursosUseCase, estados vacíos y de error) es
 * responsabilidad del Frente 3; esta pantalla solo deja la navegación y la
 * barra inferior conectadas para que ese trabajo se enchufe acá.
 */
@Composable
fun CursosScreen(
    modifier: Modifier = Modifier,
    onNavigateInicio: () -> Unit = {},
    onNavigateAvisos: () -> Unit = {},
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
            Text(text = "Cursos", color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "El listado completo de cursos lo arma el Frente 3 con datos reales.",
                color = ModusTextMuted,
                fontSize = 12.5.sp
            )
        }

        ModusBottomNavBar(
            current = BottomNavDestination.CURSOS,
            onSelect = { destination ->
                when (destination) {
                    BottomNavDestination.INICIO -> onNavigateInicio()
                    BottomNavDestination.CURSOS -> Unit
                    BottomNavDestination.AVISOS -> onNavigateAvisos()
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

@Preview(showBackground = true)
@Composable
private fun CursosScreenPreview() {
    ModusAppTheme {
        CursosScreen()
    }
}