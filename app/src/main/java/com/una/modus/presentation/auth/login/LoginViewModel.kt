package com.una.modus.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.una.modus.domain.model.User
import com.una.modus.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun login(
        email: String,
        password: String
    ) {

        if (_uiState.value.isLoading) {
            return
        }

        _uiState.value = LoginUiState(
            isLoading = true
        )

        viewModelScope.launch {

            val result = loginUseCase(
                email = email,
                password = password
            )

            result
                .onSuccess { user ->

                    _uiState.value = LoginUiState(
                        isLoading = false,
                        user = user
                    )
                }
                .onFailure { exception ->

                    _uiState.value = LoginUiState(
                        isLoading = false,
                        errorMessage =
                            exception.message
                                ?: "Ocurrió un error al iniciar sesión."
                    )
                }
        }
    }

    fun clearError() {

        _uiState.value = _uiState.value.copy(
            errorMessage = null
        )
    }

    fun consumeLoginSuccess() {

        _uiState.value = _uiState.value.copy(
            user = null
        )
    }
}