package com.una.modus.domain.repository

import com.una.modus.domain.model.Curso

/**
 * Repositorio de cursos
 *
 * Fuente de datos de los cursos del usuario. La implementación real
 * (API/base local) vive en la capa de datos; por ahora solo existe
 * [com.una.modus.data.repository.FakeCursoRepository] con datos de prueba.
 */
interface CursoRepository {
    suspend fun obtenerCursos(): List<Curso>
}
