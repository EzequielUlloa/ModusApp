package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusPrimaryText
import com.una.modus.ui.theme.ModusSurface

/**
 * Botón secundario en forma de píldora
 *
 * Variante de baja énfasis de [PrimaryGradientButton]: superficie en vez
 * de gradiente, texto en el color de marca. Se usa para la acción
 * alternativa de una pantalla (ej. "Otro curso", "Ver apuntes guardados",
 * "Limpiar búsqueda").
 *
 * @param text texto mostrado dentro del botón.
 * @param onClick callback invocado al presionar el botón.
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(ModusSurface)
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = ModusPrimaryText, fontSize = 14.sp)
    }
}
