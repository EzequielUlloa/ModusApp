package com.una.modus.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.AuthTextField
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.LinkText
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Login
 *
 * Permite al estudiante autenticarse con su correo institucional y
 * contraseña. Es puramente visual: valida y guarda el texto ingresado en
 * estado local, pero no llama a ningún backend todavía (no existe capa de
 * dominio/datos implementada aún).
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onLoginSuccess se invoca al presionar "Ingresar".
 * @param onForgotPassword se invoca al presionar "¿Olvidaste tu contraseña?".
 * @param onNavigateToRegister se invoca al presionar "Registrate".
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
) {
    // Estado local del formulario (sin validación de negocio todavía).
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            // Scroll vertical: en pantallas chicas o con letra grande el
            // contenido puede no entrar completo; sin esto quedaría cortado.
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 66.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        BackTopButton(onClick = onBack)
        Text(
            text = "Bienvenido",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Ingresá con tu correo institucional",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        AuthTextField(
            label = "Correo",
            value = email,
            onValueChange = { email = it },
            placeholder = "usuario@est.una.ac.cr",
            keyboardType = KeyboardType.Email
        )
        AuthTextField(
            label = "Contraseña",
            value = password,
            onValueChange = { password = it },
            placeholder = "••••••••",
            isPassword = true
        )
        LinkText(
            text = "¿Olvidaste tu contraseña?",
            onClick = onForgotPassword,
            textAlign = TextAlign.End
        )
        PrimaryGradientButton(text = "Ingresar", onClick = onLoginSuccess)
        LinkText(
            text = "¿No tenés cuenta? Registrate",
            onClick = onNavigateToRegister,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    ModusAppTheme {
        LoginScreen()
    }
}
