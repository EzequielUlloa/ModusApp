package com.una.modus.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Esquema de color oscuro de Modus, construido con los tokens de Color.kt.
// Es el único esquema con la marca ya aplicada (el claro sigue pendiente).
private val DarkColorScheme = darkColorScheme(
    primary = ModusButtonGradientStart,
    onPrimary = ModusOnPrimary,
    secondary = ModusLink,
    tertiary = Pink80,
    background = ModusBgGradientMid,
    onBackground = ModusText,
    surface = ModusSurface,
    onSurface = ModusText,
    surfaceVariant = ModusSurface,
    onSurfaceVariant = ModusTextMuted
)

// Esquema de color claro: todavía usa la paleta morada de plantilla porque
// el modo claro no se ha diseñado en Figma (fuera de alcance por ahora).
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

/**
 * Tema de la app Modus
 *
 * Envuelve el contenido de la app en un [MaterialTheme] con la paleta y
 * tipografía de Modus. Elige el esquema oscuro o claro según el tema del
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
