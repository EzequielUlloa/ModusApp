package com.una.modus.presentation.auth

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.AuthTextField
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.LinkText
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Recuperar contraseña
 *
 * Primer paso del flujo de recuperación: el usuario ingresa su correo
 * institucional para recibir un enlace de restablecimiento. En este
 * prototipo (sin backend) "Enviar enlace" avanza directamente a
 * [NewPasswordScreen] en vez de esperar un correo real.
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onLinkSent se invoca al presionar "Enviar enlace".
 * @param onBackToLogin se invoca al presionar "Volver a iniciar sesión".
 */
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onLinkSent: () -> Unit = {},
    onBackToLogin: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 46.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        BackTopButton(onClick = onBack)
        Text(
            text = "Recuperar contraseña",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Escribí tu correo institucional y te enviamos un enlace para restablecerla.",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        AuthTextField(
            label = "Correo institucional",
            value = email,
            onValueChange = { email = it },
            placeholder = "usuario@est.una.ac.cr",
            keyboardType = KeyboardType.Email
        )
        PrimaryGradientButton(text = "Enviar enlace", onClick = onLinkSent)
        LinkText(
            text = "Volver a iniciar sesión",
            onClick = onBackToLogin,
            textAlign = TextAlign.Center
        )
    }
}
