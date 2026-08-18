package com.una.modus.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.LinkText
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

// Cantidad de dígitos del código de verificación enviado por correo.
private const val CodeLength = 6

/**
 * Pantalla de Verificar correo
 *
 * Paso posterior al registro: el usuario ingresa el código de 6 dígitos
 * enviado a su correo institucional, dígito por dígito, con avance
 * automático de foco a la siguiente casilla. Al verificar, el flujo
 * continúa en [AccountCreatedScreen].
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onVerified se invoca al presionar "Verificar".
 * @param onResendCode se invoca al presionar "Reenviar código".
 */
@Composable
fun VerifyEmailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onVerified: () -> Unit = {},
    onResendCode: () -> Unit = {}
) {
    // Un dígito por casilla, más un FocusRequester por casilla para poder
    // saltar automáticamente a la siguiente al escribir.
    val digits = remember { mutableStateListOf(*Array(CodeLength) { "" }) }
    val focusRequesters = remember { List(CodeLength) { FocusRequester() } }

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
            text = "Verificá tu correo",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Enviamos un código de 6 dígitos a usuario@est.una.ac.cr",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        // Casillas de código OTP: una por dígito, distribuidas en partes iguales.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            digits.forEachIndexed { index, digit ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(ModusSurface, RoundedCornerShape(14.dp))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BasicTextField(
                        value = digit,
                        onValueChange = { newValue ->
                            // Solo se acepta un dígito por casilla; al completarla,
                            // el foco salta a la siguiente automáticamente.
                            val single = newValue.filter(Char::isDigit).take(1)
                            digits[index] = single
                            if (single.isNotEmpty() && index < CodeLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        },
                        modifier = Modifier
                            .focusRequester(focusRequesters[index])
                            .fillMaxWidth(),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            color = ModusText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        ),
                        cursorBrush = SolidColor(ModusText),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                    )
                }
            }
        }
        PrimaryGradientButton(text = "Verificar", onClick = onVerified)
        LinkText(
            text = "Reenviar código",
            onClick = onResendCode,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    ModusAppTheme {
        VerifyEmailScreen()
    }
}