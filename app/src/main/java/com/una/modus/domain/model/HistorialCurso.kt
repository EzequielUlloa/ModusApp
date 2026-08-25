package com.una.modus.domain.model

/**
 * Historial de un curso
 *
 * Agrupa el curso consultado junto con sus apuntes capturados, para que
 * la pantalla de Historial pida un solo dato al caso de uso en vez de
 * combinar por su cuenta el curso y su lista de apuntes.
 */
data class HistorialCurso(
    val curso: Curso,
    val apuntes: List<ApunteHistorial>
)
