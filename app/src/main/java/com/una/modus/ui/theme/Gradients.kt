package com.una.modus.ui.theme

import androidx.compose.ui.graphics.Brush

/**
 * Gradiente de fondo
 *
 * Degradado diagonal (verde oscuro → morado oscuro) usado como fondo de
 * todas las pantallas del flujo de Acceso y cuenta en modo oscuro.
 */
val ModusBackgroundGradient = Brush.linearGradient(
    colors = listOf(ModusBgGradientStart, ModusBgGradientMid, ModusBgGradientEnd)
)

/**
 * Gradiente de botón primario
 *
 * Degradado morado usado como fondo de los botones de acción principal
 * (Ingresar, Crear cuenta, Verificar, etc.).
 */
val ModusButtonGradient = Brush.linearGradient(
    colors = listOf(ModusButtonGradientStart, ModusButtonGradientEnd)
)

/**
 * Gradiente de acento
 *
 * Degradado verde usado en elementos de éxito/confirmación, como el
 * círculo de check en "Cuenta creada" y el relleno de la barra de carga
 * del splash.
 */
val ModusAccentGradient = Brush.linearGradient(
    colors = listOf(ModusAccentGradientStart, ModusAccentGradientEnd)
)
