package com.una.modus.presentation.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Pantalla de inicio (Home)
 *
 * Destino al que llega el usuario tras completar el login o el registro.
 * Todavía es un placeholder: el diseño de esta sección no se ha
 * implementado desde Figma, pendiente para una próxima iteración.
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home")
    }
}
