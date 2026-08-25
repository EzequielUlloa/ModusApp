package com.una.modus.presentation.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.data.repository.FakeHistorialRepository
import com.una.modus.domain.model.HistorialCurso
import com.una.modus.domain.usecase.GetHistorialUseCase
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.BottomNavDestination
import com.una.modus.presentation.common.components.EmptyStateView
import com.una.modus.presentation.common.components.ErrorStateView
import com.una.modus.presentation.common.components.ListRowCard
import com.una.modus.presentation.common.components.ModusBadge
import com.una.modus.presentation.common.components.ModusBottomNavBar
import com.una.modus.presentation.common.components.ModusFilterChip
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.presentation.common.state.UiState
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusAvatarDark
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusPrimaryText
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/** Filtro activo de la lista de apuntes de un curso. */
private enum class FiltroHistorial(val etiqueta: String) {
    TODOS("Todos"),
    ESTE_MES("Este mes"),
    FAVORITOS("Favoritos")
}

/**
 * Pantalla de Historial de apuntes
 *
 * Lista cronológica de los apuntes capturados y analizados de un curso,
 * con filtros rápidos y una miniatura (ícono de documento) por fila. Usa
 * [GetHistorialUseCase] para pedir el curso y sus apuntes en una sola
 * llamada, con su propio estado de carga.
 *
 * @param cursoId id del curso cuyo historial se muestra.
 * @param onApunteClick callback con el id del apunte tocado.
 * @param onCapturar callback del botón flotante de cámara y de "Capturar apunte".
 */
@Composable
fun HistorialScreen(
    cursoId: String,
    modifier: Modifier = Modifier,
    getHistorialUseCase: GetHistorialUseCase = remember { GetHistorialUseCase(FakeHistorialRepository()) },
    onBack: () -> Unit = {},
    onApunteClick: (String) -> Unit = {},
    onNavigateInicio: () -> Unit = {},
    onNavigateCursos: () -> Unit = {},
    onNavigateAvisos: () -> Unit = {},
    onNavigatePerfil: () -> Unit = {},
    onCapturar: () -> Unit = {}
) {
    var state by remember(cursoId) { mutableStateOf<UiState<HistorialCurso>>(UiState.Loading) }
    var reloadKey by remember { mutableIntStateOf(0) }
    var filtro by remember { mutableStateOf(FiltroHistorial.TODOS) }

    LaunchedEffect(cursoId, reloadKey) {
        state = UiState.Loading
        state = try {
            UiState.Success(getHistorialUseCase(cursoId))
        } catch (e: Exception) {
            UiState.Error(e.message ?: "No pudimos cargar el historial de este curso.")
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp)
                .padding(top = 32.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            BackTopButton(onClick = onBack)

            when (val current = state) {
                is UiState.Loading -> LoadingBlock()
                is UiState.Empty -> Unit
                is UiState.Error -> ErrorStateView(
                    title = "No pudimos cargar el historial",
                    subtitle = current.message,
                    retryText = "Reintentar",
                    onRetry = { reloadKey++ },
                    modifier = Modifier.padding(top = 40.dp)
                )
                is UiState.Success -> {
                    val historialCurso = current.data
                    val curso = historialCurso.curso
                    val apuntes = historialCurso.apuntes

                    Text(text = curso.nombre, color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
                    Text(
                        text = if (apuntes.isEmpty()) {
                            curso.profesor
                        } else {
                            "${curso.cantidadApuntes} apuntes analizados · ${curso.profesor}"
                        },
                        color = ModusTextMuted,
                        fontSize = 12.5.sp
                    )

                    if (apuntes.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(22.dp))
                                .background(ModusSurface)
                                .padding(vertical = 28.dp, horizontal = 20.dp)
                        ) {
                            EmptyStateView(
                                icon = Icons.Filled.CameraAlt,
                                title = "Sin apuntes todavía",
                                subtitle = "Capturá el primero y en unos segundos vas a ver el método de este profesor."
                            )
                        }
                        PrimaryGradientButton(text = "Capturar apunte", onClick = onCapturar)
                    } else {
                        FiltrosRow(filtro = filtro, onFiltroChange = { filtro = it })

                        val apuntesFiltrados = when (filtro) {
                            FiltroHistorial.TODOS -> apuntes
                            FiltroHistorial.ESTE_MES -> apuntes.filter { it.esDeEsteMes }
                            FiltroHistorial.FAVORITOS -> apuntes.filter { it.esFavorito }
                        }

                        if (apuntesFiltrados.isEmpty()) {
                            Text(
                                text = "No hay apuntes que coincidan con este filtro.",
                                color = ModusTextMuted,
                                fontSize = 12.5.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
                            )
                        } else {
                            apuntesFiltrados.forEach { apunte ->
                                ListRowCard(
                                    title = apunte.fecha,
                                    subtitle = patronesLabel(apunte.patronesDetectados),
                                    avatarStyle = ModusAvatarDark,
                                    avatarIcon = Icons.Filled.Description,
                                    onClick = { onApunteClick(apunte.id) },
                                    trailing = { ModusBadge(text = if (apunte.esNuevo) "Nuevo" else "—") }
                                )
                            }
                        }
                    }
                }
            }
        }

        ModusBottomNavBar(
            current = BottomNavDestination.CURSOS,
            onSelect = { destination ->
                when (destination) {
                    BottomNavDestination.INICIO -> onNavigateInicio()
                    BottomNavDestination.CURSOS -> onNavigateCursos()
                    BottomNavDestination.AVISOS -> onNavigateAvisos()
                    BottomNavDestination.PERFIL -> onNavigatePerfil()
                }
            },
            onCapturar = onCapturar,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
private fun FiltrosRow(filtro: FiltroHistorial, onFiltroChange: (FiltroHistorial) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FiltroHistorial.entries.forEach { opcion ->
            ModusFilterChip(
                text = opcion.etiqueta,
                selected = filtro == opcion,
                onClick = { onFiltroChange(opcion) }
            )
        }
    }
}

@Composable
private fun LoadingBlock() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = ModusPrimaryText)
    }
}

private fun patronesLabel(cantidad: Int): String =
    if (cantidad == 1) "1 patrón detectado" else "$cantidad patrones detectados"

@Preview(showBackground = true)
@Composable
private fun HistorialScreenPreview() {
    ModusAppTheme {
        HistorialScreen(cursoId = "calculo-i")
    }
}
