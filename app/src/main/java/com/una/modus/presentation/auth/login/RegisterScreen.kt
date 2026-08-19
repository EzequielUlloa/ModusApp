package com.una.modus.presentation.auth.login

import android.util.Patterns
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.una.modus.data.repository.FakeAuthRepository
import com.una.modus.domain.usecase.RegisterUseCase
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

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onNavigateToVerifyEmail: () -> Unit = {}
) {

    val registerViewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModelFactory(
            registerUseCase = RegisterUseCase(
                authRepository = FakeAuthRepository()
            )
        )
    )

    val uiState by
    registerViewModel.uiState.collectAsStateWithLifecycle()

    var fullName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var acceptedTerms by remember {
        mutableStateOf(false)
    }

    var fullNameError by remember {
        mutableStateOf<String?>(null)
    }

    var emailError by remember {
        mutableStateOf<String?>(null)
    }

    var passwordError by remember {
        mutableStateOf<String?>(null)
    }

    var confirmPasswordError by remember {
        mutableStateOf<String?>(null)
    }

    var termsError by remember {
        mutableStateOf<String?>(null)
    }

    fun validateForm(): Boolean {

        fullNameError =
            if (fullName.isBlank()) {
                "El nombre es obligatorio."
            } else {
                null
            }

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

        confirmPasswordError = when {

            confirmPassword.isBlank() ->
                "Confirmá tu contraseña."

            password != confirmPassword ->
                "Las contraseñas no coinciden."

            else -> null
        }

        termsError =
            if (!acceptedTerms) {
                "Debés aceptar los términos para continuar."
            } else {
                null
            }

        return fullNameError == null &&
                emailError == null &&
                passwordError == null &&
                confirmPasswordError == null &&
                termsError == null
    }

    LaunchedEffect(uiState.user) {

        if (uiState.user != null) {

            onNavigateToVerifyEmail()

            registerViewModel.consumeRegisterSuccess()
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
                vertical = 72.dp
            ),
        verticalArrangement =
            Arrangement.spacedBy(18.dp)
    ) {

        BackTopButton(
            onClick = onBack
        )

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
            onValueChange = {

                fullName = it
                fullNameError = null

                registerViewModel.clearError()
            },
            placeholder = "Josué Ulloa",
            errorMessage = fullNameError
        )

        AuthTextField(
            label = "Correo institucional",
            value = email,
            onValueChange = {

                email = it
                emailError = null

                registerViewModel.clearError()
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
                confirmPasswordError = null

                registerViewModel.clearError()
            },
            placeholder = "Mínimo 8 caracteres",
            isPassword = true,
            errorMessage = passwordError
        )

        AuthTextField(
            label = "Confirmar contraseña",
            value = confirmPassword,
            onValueChange = {

                confirmPassword = it
                confirmPasswordError = null

                registerViewModel.clearError()
            },
            placeholder = "••••••••",
            isPassword = true,
            errorMessage = confirmPasswordError
        )

        Row(
            verticalAlignment =
                Alignment.CenterVertically,
            horizontalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        color =
                            if (acceptedTerms) {
                                ModusLink
                            } else {
                                ModusSurface
                            },
                        shape =
                            RoundedCornerShape(6.dp)
                    )
                    .border(
                        width = 1.4.dp,
                        color = ModusTextMuted,
                        shape =
                            RoundedCornerShape(6.dp)
                    )
                    .clickable {

                        acceptedTerms =
                            !acceptedTerms

                        termsError = null

                        registerViewModel.clearError()
                    },
                contentAlignment =
                    Alignment.Center
            ) {

                if (acceptedTerms) {

                    Icon(
                        imageVector =
                            Icons.Filled.Check,
                        contentDescription =
                            "Términos aceptados",
                        tint = ModusOnPrimary,
                        modifier =
                            Modifier.size(14.dp)
                    )
                }
            }

            Text(
                text =
                    "Acepto los términos y el uso de mis apuntes",
                color = ModusTextMuted,
                fontSize = 12.5.sp,
                modifier =
                    Modifier.weight(1f)
            )
        }

        termsError?.let { error ->

            Text(
                text = error,
                color =
                    MaterialTheme.colorScheme.error,
                fontSize = 12.5.sp,
                fontWeight =
                    FontWeight.Medium
            )
        }

        uiState.errorMessage?.let { error ->

            Text(
                text = error,
                color =
                    MaterialTheme.colorScheme.error,
                fontSize = 12.5.sp,
                fontWeight =
                    FontWeight.Medium
            )
        }

        if (uiState.isLoading) {

            CircularProgressIndicator(
                color =
                    MaterialTheme.colorScheme.primary
            )
        }

        PrimaryGradientButton(
            text =
                if (uiState.isLoading) {
                    "Creando cuenta..."
                } else {
                    "Crear cuenta"
                },
            onClick = {

                if (validateForm()) {

                    registerViewModel.register(
                        fullName = fullName,
                        email = email,
                        password = password
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {

    ModusAppTheme {
        RegisterScreen()
    }
}