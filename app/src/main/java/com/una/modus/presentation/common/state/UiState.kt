package com.una.modus.presentation.common.state

/**
 * Estado de carga genérico
 *
 * Representa las cuatro situaciones por las que pasa cualquier pantalla
 * de listado al pedir datos a un caso de uso: cargando, éxito con datos,
 * éxito sin datos (vacío) y error. Se usa en Home, Cursos, "Elegir curso"
 * e Historial para no repetir esta máquina de estados en cada pantalla.
 */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data object Empty : UiState<Nothing>
    data class Error(val message: String) : UiState<Nothing>
}
