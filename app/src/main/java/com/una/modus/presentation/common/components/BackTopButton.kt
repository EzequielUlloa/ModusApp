package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusText

/**
 * Botón circular de "volver"
 *
 * Botón circular con flecha hacia atrás, repetido en la esquina superior
 * de casi todas las pantallas de autenticación. No navega por sí mismo:
 * delega la acción a [onClick] (normalmente `navController.popBackStack()`).
 *
 * @param onClick callback invocado al presionar el botón.
 */
@Composable
fun BackTopButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(ModusSurface)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Volver",
            tint = ModusText
        )
    }
}
