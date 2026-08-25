package com.una.modus.domain.model

/**
 * Apunte del historial
 *
 * Un elemento capturado y analizado dentro del historial de un curso
 * (pantalla "Historial de apuntes").
 *
 * @param id identificador único del apunte.
 * @param fecha fecha de captura ya formateada para mostrar (ej. "5 de agosto").
 * @param patronesDetectados cantidad de patrones de método detectados en este apunte.
 * @param esNuevo si el análisis se completó recientemente (muestra el badge "Nuevo").
 * @param esDeEsteMes si la captura ocurrió en el ciclo/mes actual (filtro "Este mes").
 * @param esFavorito si el usuario marcó este apunte como favorito (filtro "Favoritos").
 */
data class ApunteHistorial(
    val id: String,
    val fecha: String,
    val patronesDetectados: Int,
    val esNuevo: Boolean,
    val esDeEsteMes: Boolean,
    val esFavorito: Boolean
)
