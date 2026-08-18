package com.una.modus.presentation.auth.login

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.AuthTextField
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusLink
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Nueva contraseña
 *
 * Último paso del flujo de recuperación: el usuario define una nueva
 * contraseña mientras ve en vivo cuáles requisitos ya cumple. El
 * requisito "No puede ser igual a la anterior" no se puede validar sin
 * backend, así que se muestra siempre como pendiente.
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onPasswordSaved se invoca al presionar "Guardar contraseña".
 */
@Composable
fun NewPasswordScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onPasswordSaved: () -> Unit = {}
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Validación en vivo de los requisitos que sí se pueden comprobar
    // localmente, a partir del texto que el usuario va escribiendo.
    val hasMinLength = password.length >= 8
    val hasUpperAndDigit = password.any(Char::isUpperCase) && password.any(Char::isDigit)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 72.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        BackTopButton(onClick = onBack)
        Text(
            text = "Nueva contraseña",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Elegí una contraseña que no hayas usado antes.",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        AuthTextField(
            label = "Nueva contraseña",
            value = password,
            onValueChange = { password = it },
            placeholder = "Mínimo 8 caracteres",
            isPassword = true
        )
        AuthTextField(
            label = "Confirmar contraseña",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "••••••••",
            isPassword = true
        )
        Text(
            text = "REQUISITOS",
            color = ModusTextMuted,
            fontSize = 11.sp,
            letterSpacing = 1.1.sp
        )
        RequirementRow(text = "Al menos 8 caracteres", met = hasMinLength)
        RequirementRow(text = "Una mayúscula y un número", met = hasUpperAndDigit)
        RequirementRow(text = "No puede ser igual a la anterior", met = false)
        PrimaryGradientButton(text = "Guardar contraseña", onClick = onPasswordSaved)
    }
}

/**
 * Fila de requisito de contraseña
 *
 * Punto de color + texto usado dentro de la lista de "REQUISITOS": el
 * punto se pinta en verde cuando [met] es `true` y en gris cuando no.
 *
 * @param text descripción del requisito.
 * @param met si el requisito ya se cumple con el texto ingresado.
 */
@Composable
private fun RequirementRow(text: String, met: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(if (met) ModusLink else ModusTextMuted)
        )
        Text(text = text, color = ModusTextMuted, fontSize = 12.5.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun NewPasswordScreenPreview() {
    ModusAppTheme {
        NewPasswordScreen()
    }
}