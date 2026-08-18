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
import com.una.modus.ui.theme.ModusButtonGradient
import com.una.modus.ui.theme.ModusOnPrimary

/**
 * Botón primario con gradiente
 *
 * Botón de acción principal en forma de píldora, con el gradiente morado
 * de marca. Se usa para la acción destacada de cada pantalla (Ingresar,
 * Crear cuenta, Verificar, Guardar contraseña, etc.).
 *
 * @param text texto mostrado dentro del botón.
 * @param onClick callback invocado al presionar el botón.
 */
@Composable
fun PrimaryGradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(ModusButtonGradient)
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = ModusOnPrimary, fontSize = 14.sp)
    }
}
