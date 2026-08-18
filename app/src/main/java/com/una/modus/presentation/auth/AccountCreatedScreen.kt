package com.una.modus.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAccentGradient
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusOnAccent
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Cuenta creada
 *
 * Confirmación de éxito mostrada al terminar el registro (después de
 * [VerifyEmailScreen]). Al presionar "Comenzar", el grafo de navegación
 * lleva al usuario a Home y limpia el stack de autenticación.
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onStart se invoca al presionar "Comenzar".
 */
@Composable
fun AccountCreatedScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onStart: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .padding(horizontal = 28.dp, vertical = 46.dp)
    ) {
        BackTopButton(onClick = onBack, modifier = Modifier.align(Alignment.Start))
        // Bloque central: ícono de check + mensaje de éxito, centrado
        // verticalmente en el espacio restante entre el back button y el botón.
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(ModusAccentGradient)
                    .padding(horizontal = 30.dp, vertical = 26.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✓", color = ModusOnAccent, fontSize = 34.sp, fontWeight = FontWeight.SemiBold)
            }
            Text(
                text = "¡Cuenta creada!",
                color = ModusText,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Ya podés empezar a analizar los apuntes de tus cursos.",
                color = ModusTextMuted,
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        PrimaryGradientButton(text = "Comenzar", onClick = onStart)
    }
}
