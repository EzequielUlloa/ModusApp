package com.una.modus.domain.usecase

import com.una.modus.domain.model.HistorialCurso
import com.una.modus.domain.repository.HistorialRepository

/**
 * Caso de uso: obtener historial
 *
 * Pide al [HistorialRepository] el curso y sus apuntes capturados, para
 * la pantalla de Historial de apuntes.
 */
class GetHistorialUseCase(private val repository: HistorialRepository) {
    suspend operator fun invoke(cursoId: String): HistorialCurso = repository.obtenerHistorial(cursoId)
}
