package com.una.modus.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.una.modus.domain.model.User
import com.una.modus.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(RegisterUiState())

    val uiState: StateFlow<RegisterUiState> =
        _uiState.asStateFlow()

    fun register(
        fullName: String,
        email: String,
        password: String
    ) {

        if (_uiState.value.isLoading) {
            return
        }

        _uiState.value = RegisterUiState(
            isLoading = true
        )

        viewModelScope.launch {

            val result = registerUseCase(
                fullName = fullName,
                email = email,
                password = password
            )

            result
                .onSuccess { user ->

                    _uiState.value = RegisterUiState(
                        isLoading = false,
                        user = user
                    )
                }
                .onFailure { exception ->

                    _uiState.value = RegisterUiState(
                        isLoading = false,
                        errorMessage =
                            exception.message
                                ?: "Ocurrió un error al crear la cuenta."
                    )
                }
        }
    }

    fun clearError() {

        _uiState.value =
            _uiState.value.copy(
                errorMessage = null
            )
    }

    fun consumeRegisterSuccess() {

        _uiState.value =
            _uiState.value.copy(
                user = null
            )
    }
}