package com.una.modus.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Gradiente de fondo
 *
 * Degradado diagonal usado como fondo de todas las pantallas del flujo de
 * Acceso y cuenta: verde oscuro → morado oscuro en modo oscuro, o su
 * espejo claro en modo claro (según [LocalModusColors]).
 */
val ModusBackgroundGradient: Brush
    @Composable
    @ReadOnlyComposable
    get() {
        val palette = LocalModusColors.current
        return Brush.linearGradient(
            colors = listOf(palette.bgGradientStart, palette.bgGradientMid, palette.bgGradientEnd)
        )
    }

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

/**
 * Estilo de avatar de curso/opción
 *
 * Par (degradado de fondo + color de texto/ícono) usado en los avatares
 * circulares/redondeados de [ListRowCard] — cada curso u opción del menú
 * de Perfil toma uno de estos cuatro estilos, tal como en Figma.
 */
data class AvatarStyle(val gradient: Brush, val onColor: Color)

val ModusAvatarTeal = AvatarStyle(
    gradient = Brush.linearGradient(listOf(Color(0xFF9BE8DC), Color(0xFF3FBFAD))),
    onColor = Color(0xFF04231F)
)
val ModusAvatarBlue = AvatarStyle(
    gradient = Brush.linearGradient(listOf(Color(0xFFA9C8FF), Color(0xFF5B84E8))),
    onColor = Color(0xFF0A1430)
)
val ModusAvatarOrange = AvatarStyle(
    gradient = Brush.linearGradient(listOf(Color(0xFFFFD08A), Color(0xFFE8A33F))),
    onColor = Color(0xFF2A1A05)
)
val ModusAvatarPurple = AvatarStyle(
    gradient = Brush.linearGradient(listOf(Color(0xFFC3A9FF), Color(0xFF8B6CF7))),
    onColor = Color(0xFF160E33)
)

/**
 * Avatar oscuro sólido usado en las filas de Historial: representa la
 * miniatura de la hoja capturada (ícono de documento sobre fondo oscuro),
 * a diferencia de los avatares con iniciales de curso.
 */
val ModusAvatarDark = AvatarStyle(
    gradient = Brush.linearGradient(listOf(Color(0xFF0D1410), Color(0xFF0D1410))),
    onColor = Color(0xFFE9E5D9)
)
