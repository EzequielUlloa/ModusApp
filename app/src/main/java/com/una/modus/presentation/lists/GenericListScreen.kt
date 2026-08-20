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
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de lista genérica
 *
 * Plantilla reutilizable para futuras pantallas de listado (cursos,
 * apuntes, profesores, etc.). Hoy funciona como destino temporal del FAB
 * "Capturar" y de tocar un curso en el Home — el flujo real (Elegir
 * curso / captura) es responsabilidad del Frente 3, que debe reemplazar
 * este contenido sin tener que tocar la navegación que ya la trae hasta acá.
 *
 * @param onBack se invoca al presionar el botón de volver.
 */
@Composable
fun GenericListScreen(modifier: Modifier = Modifier, onBack: () -> Unit = {}) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .padding(horizontal = 28.dp, vertical = 24.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
            BackTopButton(onClick = onBack)
            Text(text = "Lista", color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Acá va el flujo real (elegir curso / captura) que arma el Frente 3.",
                color = ModusTextMuted,
                fontSize = 12.5.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GenericListScreenPreview() {
    ModusAppTheme {
        GenericListScreen()
    }
}