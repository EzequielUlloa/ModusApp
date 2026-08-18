package com.una.modus.presentation.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusLink

/**
 * Texto de enlace
 *
 * Texto clickeable en verde de marca, usado para acciones secundarias
 * como "¿Olvidaste tu contraseña?", "Registrate" o "Reenviar código".
 *
 * @param text texto del enlace.
 * @param onClick callback invocado al presionar el texto.
 * @param textAlign alineación del texto dentro de su ancho disponible.
 */
@Composable
fun LinkText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        color = ModusLink,
        fontSize = 12.5.sp,
        textAlign = textAlign,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    )
}
