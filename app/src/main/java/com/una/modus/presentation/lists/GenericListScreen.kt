package com.una.modus.presentation.lists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Pantalla de lista genérica
 *
 * Pensada como plantilla reutilizable para futuras pantallas de listado
 * (cursos, apuntes, profesores, etc.). Todavía es un placeholder sin
 * diseño ni datos reales conectados.
 */
@Composable
fun GenericListScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Lista")
    }
}
