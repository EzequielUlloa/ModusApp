package com.una.modus.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.data.repository.FakeCursoRepository
import com.una.modus.domain.model.Curso
import com.una.modus.domain.usecase.GetCursosUseCase
import com.una.modus.presentation.common.components.BottomNavDestination
import com.una.modus.presentation.common.components.CourseProgressRing
import com.una.modus.presentation.common.components.ListRowCard
import com.una.modus.presentation.common.components.ModusBottomNavBar
import com.una.modus.presentation.common.components.avatarStyleForIndex
import com.una.modus.presentation.common.state.UiState
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusButtonGradient
import com.una.modus.ui.theme.ModusOnPrimary
import com.una.modus.ui.theme.ModusPrimaryText
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de inicio (Home)
 *
 * Destino al que llega el usuario tras completar el login o el registro.
 * Muestra el saludo, el acceso directo a "Analizar un apunte nuevo" y la
 * lista de cursos activos (vía [GetCursosUseCase], con su propio estado
 * de carga), con la barra de navegación inferior fija.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userName: String = "Josué",
    getCursosUseCase: GetCursosUseCase = remember { GetCursosUseCase(FakeCursoRepository()) },
    onNavigateCursos: () -> Unit = {},
    onNavigateAvisos: () -> Unit = {},
    onNavigatePerfil: () -> Unit = {},
    onCapturar: () -> Unit = {},
    onCourseClick: (String) -> Unit = {}
) {
    var state by remember { mutableStateOf<UiState<List<Curso>>>(UiState.Loading) }

    LaunchedEffect(Unit) {
        state = try {
            val cursos = getCursosUseCase()
            if (cursos.isEmpty()) UiState.Empty else UiState.Success(cursos)
        } catch (e: Exception) {
            UiState.Error(e.message ?: "No pudimos cargar tus cursos.")
        }
    }
    val cursosActivos = (state as? UiState.Success)?.data.orEmpty()

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
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(text = "Hola, $userName", color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "II Ciclo 2026 · ${cursosActivos.size} cursos activos",
                color = ModusTextMuted,
                fontSize = 12.5.sp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .background(ModusButtonGradient)
                    .clickable(onClick = onCapturar)
                    .padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Analizar un apunte nuevo",
                    color = ModusOnPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Tomá una foto de la pizarra o del cuaderno y Modus detecta el método.",
                    color = ModusOnPrimary,
                    fontSize = 12.5.sp
                )
            }

            Text(
                text = "TUS CURSOS",
                color = ModusTextMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.1.sp
            )

            when (state) {
                is UiState.Loading -> Box(modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = ModusPrimaryText)
                }
                is UiState.Empty -> Text(
                    text = "Todavía no tenés cursos. Agregá uno desde la pestaña Cursos.",
                    color = ModusTextMuted,
                    fontSize = 12.5.sp
                )
                is UiState.Error -> Text(
                    text = "No pudimos cargar tus cursos ahora mismo.",
                    color = ModusTextMuted,
                    fontSize = 12.5.sp
                )
                is UiState.Success -> Unit
            }

            cursosActivos.forEachIndexed { index, curso ->
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

        ModusBottomNavBar(
            current = BottomNavDestination.INICIO,
            onSelect = { destination ->
                when (destination) {
                    BottomNavDestination.INICIO -> Unit
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
