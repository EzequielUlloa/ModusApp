package com.una.modus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.una.modus.presentation.common.navigation.ModusNavGraph
import com.una.modus.ui.theme.ModusAppTheme

/**
 * Actividad principal
 *
 * Único punto de entrada de la app. Aplica el tema de Modus y monta el
 * grafo de navegación ([ModusNavGraph]) dentro de un [Scaffold] a pantalla
 * completa; no contiene lógica de UI propia.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModusAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ModusNavGraph(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
