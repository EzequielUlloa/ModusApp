package com.una.modus.domain.usecase

import com.una.modus.domain.model.Curso
import com.una.modus.domain.repository.CursoRepository

/**
 * Caso de uso: obtener cursos
 *
 * Pide al [CursoRepository] la lista de cursos activos del usuario.
 * Usado por Home, la pestaña Cursos y el selector de "Elegir curso" para
 * no duplicar la carga de datos entre esas pantallas.
 */
class GetCursosUseCase(private val repository: CursoRepository) {
    suspend operator fun invoke(): List<Curso> = repository.obtenerCursos()
}
