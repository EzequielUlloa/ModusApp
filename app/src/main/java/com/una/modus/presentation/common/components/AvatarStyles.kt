package com.una.modus.presentation.common.components

import com.una.modus.ui.theme.AvatarStyle
import com.una.modus.ui.theme.ModusAvatarBlue
import com.una.modus.ui.theme.ModusAvatarOrange
import com.una.modus.ui.theme.ModusAvatarPurple
import com.una.modus.ui.theme.ModusAvatarTeal

/** Ciclo de estilos de avatar de curso, en el mismo orden que usa Figma. */
private val cursoAvatarStyles = listOf(ModusAvatarTeal, ModusAvatarBlue, ModusAvatarOrange, ModusAvatarPurple)

/**
 * Estilo de avatar para el curso en la posición [index] de una lista.
 *
 * Los cursos son datos del dominio (sin conocer colores de UI), así que
 * el color de su avatar se asigna acá por posición, cíclicamente, en vez
 * de guardarse en [com.una.modus.domain.model.Curso]. Se reutiliza en
 * Home, Cursos y "Elegir curso" para que los mismos cursos siempre
 * muestren el mismo color.
 */
fun avatarStyleForIndex(index: Int): AvatarStyle = cursoAvatarStyles[index % cursoAvatarStyles.size]
