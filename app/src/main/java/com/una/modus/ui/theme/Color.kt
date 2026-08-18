package com.una.modus.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Paleta Modus · tokens que cambian según el tema
 *
 * Agrupa los colores cuyo valor depende de si el dispositivo está en modo
 * claro u oscuro (fondo, superficies, texto, enlaces, etc.). Los tokens de
 * marca que NO cambian con el tema (gradiente de botón primario, colores
 * "on-color" sobre esos gradientes, ilustraciones) siguen siendo
 * constantes sueltas más abajo en este archivo.
 */
internal data class ModusColorPalette(
    val bgGradientStart: Color,
    val bgGradientMid: Color,
    val bgGradientEnd: Color,
    val surface: Color,
    val text: Color,
    val textMuted: Color,
    val link: Color,
    val progressTrack: Color,
    val activeTab: Color
)

/**
 * Paleta Modus · Modo oscuro
 *
 * Tokens de color extraídos directamente del archivo de Figma
 * ("OSCURO · Acceso y cuenta").
 */
internal val ModusDarkPalette = ModusColorPalette(
    bgGradientStart = Color(0xFF17240F),
    bgGradientMid = Color(0xFF111C16),
    bgGradientEnd = Color(0xFF141026),
    surface = Color(0xFF1C2A18),
    text = Color(0xFFEDF7EA),
    textMuted = Color(0xFFB2C4AD),
    link = Color(0xFF6EEA8E),
    progressTrack = Color(0xFF3D5636),
    // Color del tab activo en la barra de navegación inferior, lila claro
    // sobre la superficie oscura de la barra.
    activeTab = Color(0xFFBEACFF)
)

/**
 * Paleta Modus · Modo claro
 *
 * Espejo del modo oscuro: mismo lenguaje de marca (fondo con tinte
 * verde/morado, acentos verde y morado) invertido a fondo claro, con los
 * tonos de enlace y tab activo oscurecidos para mantener contraste
 * legible sobre superficies blancas/claras.
 */
internal val ModusLightPalette = ModusColorPalette(
    bgGradientStart = Color(0xFFF2F7ED),
    bgGradientMid = Color(0xFFFCFBFF),
    bgGradientEnd = Color(0xFFF0ECFA),
    surface = Color(0xFFFFFFFF),
    text = Color(0xFF1B2A17),
    textMuted = Color(0xFF5B6B57),
    link = Color(0xFF1F9D4C),
    progressTrack = Color(0xFFDCE6D6),
    activeTab = Color(0xFF6A4FD1)
)

/** Paleta Modus vigente en la composición actual; la provee [ModusAppTheme]. */
internal val LocalModusColors = staticCompositionLocalOf { ModusDarkPalette }

// Los siguientes tokens leen de LocalModusColors, así que su valor cambia
// automáticamente según el tema activo, pero cada pantalla los sigue usando
// igual que antes (como si fueran constantes): `color = ModusText`, etc.

val ModusSurface: Color
    @Composable
    @ReadOnlyComposable
    get() = LocalModusColors.current.surface

val ModusText: Color
    @Composable
    @ReadOnlyComposable
    get() = LocalModusColors.current.text

val ModusTextMuted: Color
    @Composable
    @ReadOnlyComposable
    get() = LocalModusColors.current.textMuted

val ModusLink: Color
    @Composable
    @ReadOnlyComposable
    get() = LocalModusColors.current.link

val ModusProgressTrack: Color
    @Composable
    @ReadOnlyComposable
    get() = LocalModusColors.current.progressTrack

// Color del tab activo en la barra de navegación inferior ("Inicio"/"Perfil"
// resaltados en Figma con un lila más claro que el resto de los íconos).
val ModusPrimaryText: Color
    @Composable
    @ReadOnlyComposable
    get() = LocalModusColors.current.activeTab

/**
 * Tokens de marca Modus · constantes (no cambian con el tema)
 *
 * Colores de los gradientes de marca y de los textos/íconos que van
 * *sobre* esos gradientes: al ser siempre el mismo fondo saturado
 * (morado o verde), su contraste no depende de si el resto de la pantalla
 * está en modo claro u oscuro.
 */
val ModusOnPrimary = Color(0xFFFBFAFE)
val ModusButtonGradientStart = Color(0xFF7E63EE)
val ModusButtonGradientEnd = Color(0xFF4A2FC0)
val ModusAccentGradientStart = Color(0xFF7DF09B)
val ModusAccentGradientEnd = Color(0xFF3FCB74)
val ModusOnAccent = Color(0xFF0B1A11)

/**
 * Paleta Modus · Onboarding
 *
 * Tokens adicionales extraídos de la sección "OSCURO · Onboarding" de
 * Figma, usados solo en las formas decorativas de las ilustraciones de
 * bienvenida. Al ser bloques de color saturado y puramente decorativos,
 * se mantienen iguales en ambos temas.
 */
val ModusIllustrationPrimary = Color(0xFF826CF6)
val ModusIllustrationDeepGreen = Color(0xFF3FA850)
