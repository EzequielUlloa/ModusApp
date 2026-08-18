package com.una.modus.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

// Esquema de color oscuro de Modus, construido con los tokens de Color.kt.
private val DarkColorScheme = darkColorScheme(
    primary = ModusButtonGradientStart,
    onPrimary = ModusOnPrimary,
    secondary = ModusDarkPalette.link,
    tertiary = ModusIllustrationPrimary,
    background = ModusDarkPalette.bgGradientMid,
    onBackground = ModusDarkPalette.text,
    surface = ModusDarkPalette.surface,
    onSurface = ModusDarkPalette.text,
    surfaceVariant = ModusDarkPalette.surface,
    onSurfaceVariant = ModusDarkPalette.textMuted
)

// Esquema de color claro de Modus, espejo del oscuro con los mismos tokens
// de marca (ver ModusLightPalette en Color.kt).
private val LightColorScheme = lightColorScheme(
    primary = ModusButtonGradientStart,
    onPrimary = ModusOnPrimary,
    secondary = ModusLightPalette.link,
    tertiary = ModusIllustrationPrimary,
    background = ModusLightPalette.bgGradientMid,
    onBackground = ModusLightPalette.text,
    surface = ModusLightPalette.surface,
    onSurface = ModusLightPalette.text,
    surfaceVariant = ModusLightPalette.surface,
    onSurfaceVariant = ModusLightPalette.textMuted
)

/**
 * Tema de la app Modus
 *
 * Envuelve el contenido de la app en un [MaterialTheme] con la paleta y
 * tipografía de Modus, y expone esa misma paleta a las pantallas vía
 * [LocalModusColors] (de donde leen tokens como [ModusText] o
 * [ModusSurface]). Elige el esquema oscuro o claro según el tema del
 * sistema, y deja el color dinámico (Material You) desactivado por
 * defecto para que la marca de Modus no sea reemplazada en Android 12+.
 *
 * @param darkTheme si se debe usar el esquema oscuro; por defecto sigue al sistema.
 * @param dynamicColor si se debe usar la paleta dinámica de Android 12+ en vez de la de marca.
 */
@Composable
fun ModusAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Disabled by default: Material You would otherwise override Modus's brand palette/gradients on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val modusColors = if (darkTheme) ModusDarkPalette else ModusLightPalette

    CompositionLocalProvider(LocalModusColors provides modusColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
