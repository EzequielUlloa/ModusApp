package com.una.modus.domain.model

/**
 * Curso del usuario
 *
 * Representa un curso activo del estudiante, tal como aparece en "Tus
 * cursos" del Home, en la pestaña Cursos y en el selector de "Elegir
 * curso" antes de capturar un apunte.
 *
 * @param id identificador único del curso.
 * @param codigo sigla mostrada en el avatar (ej. "CI").
 * @param nombre nombre completo del curso.
 * @param profesor nombre del profesor a cargo.
 * @param cantidadApuntes cantidad de apuntes ya analizados de este curso.
 * @param progreso porcentaje de avance del método detectado, 0..100.
 */
data class Curso(
    val id: String,
    val codigo: String,
    val nombre: String,
    val profesor: String,
    val cantidadApuntes: Int,
    val progreso: Int
)
