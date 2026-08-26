package com.una.modus.domain.repository

import com.una.modus.domain.model.HistorialCurso

/**
 * Repositorio de historial
 *
 * Fuente de datos del historial de apuntes capturados de un curso. La
 * implementación real vive en la capa de datos; por ahora solo existe
 * [com.una.modus.data.repository.FakeHistorialRepository] con datos de prueba.
 */
interface HistorialRepository {
    suspend fun obtenerHistorial(cursoId: String): HistorialCurso
}
