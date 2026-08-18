package com.una.modus.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.AuthTextField
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusLink
import com.una.modus.ui.theme.ModusOnPrimary
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Registro
 *
 * Formulario de creación de cuenta para estudiantes (nombre, correo
 * institucional, contraseña y aceptación de términos). Al confirmar,
 * el flujo continúa en [VerifyEmailScreen] para validar el correo.
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onNavigateToVerifyEmail se invoca al presionar "Crear cuenta".
 */
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onNavigateToVerifyEmail: () -> Unit = {}
) {
    // Estado local del formulario (sin validación de negocio todavía).
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            // Scroll vertical: este formulario es el más largo del flujo
            // (4 campos + checkbox + botón) y no entra en pantallas chicas.
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 72.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        BackTopButton(onClick = onBack)
        Text(
            text = "Crear cuenta",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Solo estudiantes con correo institucional",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        AuthTextField(
            label = "Nombre completo",
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Josué Ulloa"
        )
        AuthTextField(
            label = "Correo institucional",
            value = email,
            onValueChange = { email = it },
            placeholder = "usuario@est.una.ac.cr",
            keyboardType = KeyboardType.Email
        )
        AuthTextField(
            label = "Contraseña",
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
        // Checkbox de términos: no hay componente Checkbox de marca todavía,
        // así que se arma a mano con un Box cuadrado + ícono de check.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        if (acceptedTerms) ModusLink else ModusSurface,
                        RoundedCornerShape(6.dp)
                    )
                    .border(1.4.dp, ModusTextMuted, RoundedCornerShape(6.dp))
                    .clickable { acceptedTerms = !acceptedTerms },
                contentAlignment = Alignment.Center
            ) {
                if (acceptedTerms) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = ModusOnPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            Text(
                text = "Acepto los términos y el uso de mis apuntes",
                color = ModusTextMuted,
                fontSize = 12.5.sp,
                modifier = Modifier.weight(1f)
            )
        }
        PrimaryGradientButton(text = "Crear cuenta", onClick = onNavigateToVerifyEmail)
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    ModusAppTheme {
        RegisterScreen()
    }
}