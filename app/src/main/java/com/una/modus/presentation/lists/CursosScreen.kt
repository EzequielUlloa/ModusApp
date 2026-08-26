package com.una.modus.presentation.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.data.repository.FakeCursoRepository
import com.una.modus.domain.model.Curso
import com.una.modus.domain.usecase.GetCursosUseCase
import com.una.modus.presentation.common.components.BottomNavDestination
import com.una.modus.presentation.common.components.CourseProgressRing
import com.una.modus.presentation.common.components.EmptyStateView
import com.una.modus.presentation.common.components.ErrorStateView
import com.una.modus.presentation.common.components.ListRowCard
import com.una.modus.presentation.common.components.ModusBottomNavBar
import com.una.modus.presentation.common.components.avatarStyleForIndex
import com.una.modus.presentation.common.state.UiState
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusPrimaryText
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Cursos
 *
 * Destino de la pestaña "Cursos" de la barra inferior: lista completa de
 * cursos activos del usuario, cada uno navegable hacia su historial de
 * apuntes. Usa [GetCursosUseCase] con sus estados de carga/vacío/error.
 *
 * @param onCourseClick callback con el id del curso tocado.
 * @param onAddCourse callback del botón "Agregar curso" del estado vacío.
 */
@Composable
fun CursosScreen(
    modifier: Modifier = Modifier,
    getCursosUseCase: GetCursosUseCase = remember { GetCursosUseCase(FakeCursoRepository()) },
    onNavigateInicio: () -> Unit = {},
    onNavigateAvisos: () -> Unit = {},
    onNavigatePerfil: () -> Unit = {},
    onCapturar: () -> Unit = {},
    onCourseClick: (String) -> Unit = {},
    onAddCourse: () -> Unit = {}
) {
    var state by remember { mutableStateOf<UiState<List<Curso>>>(UiState.Loading) }
    var reloadKey by remember { mutableIntStateOf(0) }

    LaunchedEffect(reloadKey) {
        state = UiState.Loading
        state = try {
            val cursos = getCursosUseCase()
            if (cursos.isEmpty()) UiState.Empty else UiState.Success(cursos)
        } catch (e: Exception) {
            UiState.Error(e.message ?: "No pudimos cargar tus cursos.")
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
                .padding(top = 78.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(text = "Cursos", color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)

            when (val current = state) {
                is UiState.Loading -> {
                    Text(text = "Cargando tus cursos…", color = ModusTextMuted, fontSize = 12.5.sp)
                    Box(modifier = Modifier.fillMaxWidth().padding(top = 60.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = ModusPrimaryText)
                    }
                }
                is UiState.Empty -> {
                    Text(text = "Todavía no tenés cursos activos.", color = ModusTextMuted, fontSize = 12.5.sp)
                    EmptyStateView(
                        icon = Icons.Filled.Add,
                        title = "Todavía no tenés cursos",
                        subtitle = "Agregá tu primer curso para empezar a analizar los apuntes de ese profesor.",
                        actionText = "Agregar curso",
                        onAction = onAddCourse,
                        modifier = Modifier.padding(top = 40.dp)
                    )
                }
                is UiState.Error -> {
                    Text(text = "No pudimos cargar tus cursos.", color = ModusTextMuted, fontSize = 12.5.sp)
                    ErrorStateView(
                        title = "No pudimos cargar tus cursos",
                        subtitle = current.message,
                        retryText = "Reintentar",
                        onRetry = { reloadKey++ },
                        modifier = Modifier.padding(top = 40.dp)
                    )
                }
                is UiState.Success -> {
                    val cursos = current.data
                    Text(text = "${cursos.size} cursos activos", color = ModusTextMuted, fontSize = 12.5.sp)
                    cursos.forEachIndexed { index, curso ->
                        ListRowCard(
                            title = curso.nombre,
                            subtitle = "${curso.profesor} · ${curso.cantidadApuntes} apuntes",
                            avatarStyle = avatarStyleForIndex(index),
                            avatarLabel = curso.codigo,
                            onClick = { onCourseClick(curso.id) },
                            trailing = { CourseProgressRing(progress = curso.progreso) }
                        )
                    }
                }
            }
        }

        ModusBottomNavBar(
            current = BottomNavDestination.CURSOS,
            onSelect = { destination ->
                when (destination) {
                    BottomNavDestination.INICIO -> onNavigateInicio()
                    BottomNavDestination.CURSOS -> Unit
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

@Preview(showBackground = true)
@Composable
private fun CursosScreenPreview() {
    ModusAppTheme {
        CursosScreen()
    }
}
