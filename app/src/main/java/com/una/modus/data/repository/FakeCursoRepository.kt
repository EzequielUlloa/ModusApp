package com.una.modus.data.repository

import com.una.modus.domain.model.Curso
import com.una.modus.domain.repository.CursoRepository
import kotlinx.coroutines.delay

/**
 * Repositorio de cursos · datos de prueba
 *
 * Implementación temporal de [CursoRepository] con los cursos de muestra
 * del prototipo de Figma. El `delay` simula la latencia de una fuente de
 * datos real para que las pantallas puedan mostrar su indicador de carga;
 * se reemplaza por una implementación con API/base local más adelante.
 */
class FakeCursoRepository : CursoRepository {

    override suspend fun obtenerCursos(): List<Curso> {
        delay(700)
        return cursos
    }

    companion object {
        val cursos = listOf(
            Curso(
                id = "calculo-i",
                codigo = "CI",
                nombre = "Cálculo I",
                profesor = "Prof. Róger León",
                cantidadApuntes = 12,
                progreso = 78
            ),
            Curso(
                id = "fisica-general",
                codigo = "FG",
                nombre = "Física General",
                profesor = "Prof. Maikol Guzmán",
                cantidadApuntes = 8,
                progreso = 64
            ),
            Curso(
                id = "bases-de-datos",
                codigo = "BD",
                nombre = "Bases de Datos",
                profesor = "Prof. Ana Solano",
                cantidadApuntes = 5,
                progreso = 41
            ),
            Curso(
                id = "algebra-lineal",
                codigo = "AL",
                nombre = "Álgebra Lineal",
                profesor = "Prof. Luis Vargas",
                cantidadApuntes = 0,
                progreso = 0
            )
        )
    }
}
