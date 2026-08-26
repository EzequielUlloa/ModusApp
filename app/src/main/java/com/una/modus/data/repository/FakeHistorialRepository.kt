package com.una.modus.data.repository

import com.una.modus.domain.model.ApunteHistorial
import com.una.modus.domain.model.HistorialCurso
import com.una.modus.domain.repository.HistorialRepository
import kotlinx.coroutines.delay

/**
 * Repositorio de historial · datos de prueba
 *
 * Implementación temporal de [HistorialRepository] con apuntes de muestra
 * por curso (algunos cursos quedan sin apuntes a propósito, para poder
 * probar el estado vacío de la pantalla de Historial). El `delay` simula
 * la latencia de una fuente de datos real.
 */
class FakeHistorialRepository(
    private val cursoRepository: FakeCursoRepository = FakeCursoRepository()
) : HistorialRepository {

    override suspend fun obtenerHistorial(cursoId: String): HistorialCurso {
        delay(700)
        val curso = cursoRepository.obtenerCursos().firstOrNull { it.id == cursoId }
            ?: throw NoSuchElementException("Curso no encontrado: $cursoId")
        return HistorialCurso(curso = curso, apuntes = apuntesPorCurso[cursoId].orEmpty())
    }

    companion object {
        private val apuntesPorCurso: Map<String, List<ApunteHistorial>> = mapOf(
            "calculo-i" to listOf(
                ApunteHistorial(id = "ci-1", fecha = "5 de agosto", patronesDetectados = 3, esNuevo = true, esDeEsteMes = true, esFavorito = true),
                ApunteHistorial(id = "ci-2", fecha = "1 de agosto", patronesDetectados = 2, esNuevo = false, esDeEsteMes = true, esFavorito = false),
                ApunteHistorial(id = "ci-3", fecha = "28 de julio", patronesDetectados = 4, esNuevo = false, esDeEsteMes = false, esFavorito = true),
                ApunteHistorial(id = "ci-4", fecha = "24 de julio", patronesDetectados = 1, esNuevo = false, esDeEsteMes = false, esFavorito = false)
            ),
            "fisica-general" to listOf(
                ApunteHistorial(id = "fg-1", fecha = "3 de agosto", patronesDetectados = 2, esNuevo = true, esDeEsteMes = true, esFavorito = false),
                ApunteHistorial(id = "fg-2", fecha = "20 de julio", patronesDetectados = 1, esNuevo = false, esDeEsteMes = false, esFavorito = false)
            ),
            "bases-de-datos" to listOf(
                ApunteHistorial(id = "bd-1", fecha = "15 de julio", patronesDetectados = 1, esNuevo = false, esDeEsteMes = false, esFavorito = false)
            ),
            "algebra-lineal" to emptyList()
        )
    }
}
