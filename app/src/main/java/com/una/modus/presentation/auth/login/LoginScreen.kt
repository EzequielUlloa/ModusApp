package com.una.modus.presentation.auth.login

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.una.modus.data.repository.FakeAuthRepository
import com.una.modus.domain.usecase.LoginUseCase
import com.una.modus.presentation.common.components.AuthTextField
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.LinkText
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Login de Modus.
 *
 * Permite al estudiante autenticarse utilizando su correo institucional
 * y contraseña.
 *
 * Actualmente utiliza un repositorio falso porque todavía no existe
 * conexión con el backend.
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
) {

    /*
     * Dependencias temporales.
     *
     * Más adelante FakeAuthRepository puede reemplazarse por la
     * implementación real sin modificar LoginViewModel ni LoginUseCase.
     */
    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(
            loginUseCase = LoginUseCase(
                authRepository = FakeAuthRepository()
            )
        )
    )

    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var emailError by remember {
        mutableStateOf<String?>(null)
    }

    var passwordError by remember {
        mutableStateOf<String?>(null)
    }

    /**
     * Valida únicamente cuestiones propias del formulario.
     *
     * La comprobación de las credenciales no pertenece a la pantalla.
     */
    fun validateForm(): Boolean {

        emailError = when {

            email.isBlank() ->
                "El correo es obligatorio."

            !Patterns.EMAIL_ADDRESS
                .matcher(email.trim())
                .matches() ->
                "Ingresá un correo válido."

            !email.trim()
                .lowercase()
                .endsWith("@est.una.ac.cr") ->
                "Debés utilizar tu correo institucional @est.una.ac.cr"

            else -> null
        }

        passwordError = when {

            password.isBlank() ->
                "La contraseña es obligatoria."

            password.length < 8 ->
                "La contraseña debe tener al menos 8 caracteres."

            else -> null
        }

        return emailError == null &&
                passwordError == null
    }

    /*
     * Cuando el ViewModel informa que existe un usuario autenticado,
     * se ejecuta la navegación.
     */
    LaunchedEffect(uiState.user) {

        if (uiState.user != null) {

            onLoginSuccess()

            loginViewModel.consumeLoginSuccess()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .verticalScroll(
                rememberScrollState()
            )
            .padding(
                horizontal = 28.dp,
                vertical = 66.dp
            ),
        verticalArrangement =
            Arrangement.spacedBy(18.dp)
    ) {

        BackTopButton(
            onClick = onBack
        )

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
            onValueChange = {

                email = it

                emailError = null

                loginViewModel.clearError()
            },
            placeholder = "usuario@est.una.ac.cr",
            keyboardType = KeyboardType.Email,
            errorMessage = emailError
        )

        AuthTextField(
            label = "Contraseña",
            value = password,
            onValueChange = {

                password = it

                passwordError = null

                loginViewModel.clearError()
            },
            placeholder = "••••••••",
            isPassword = true,
            errorMessage = passwordError
        )

        LinkText(
            text = "¿Olvidaste tu contraseña?",
            onClick = onForgotPassword,
            textAlign = TextAlign.End
        )

        /*
         * Error producido por FakeAuthRepository.
         */
        uiState.errorMessage?.let { error ->

            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.5.sp,
                fontWeight = FontWeight.Medium
            )
        }

        /*
         * Indicador mientras LoginViewModel está procesando
         * la autenticación.
         */
        if (uiState.isLoading) {

            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }

        PrimaryGradientButton(
            text =
                if (uiState.isLoading) {
                    "Ingresando..."
                } else {
                    "Ingresar"
                },
            onClick = {

                if (validateForm()) {

                    loginViewModel.login(
                        email = email,
                        password = password
                    )
                }
            }
        )

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