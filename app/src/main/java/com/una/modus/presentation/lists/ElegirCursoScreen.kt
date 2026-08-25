package com.una.modus.presentation.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.data.repository.FakeCursoRepository
import com.una.modus.domain.model.Curso
import com.una.modus.domain.usecase.GetCursosUseCase
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.EmptyStateView
import com.una.modus.presentation.common.components.ErrorStateView
import com.una.modus.presentation.common.components.ListRowCard
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.presentation.common.components.SecondaryButton
import com.una.modus.presentation.common.components.avatarStyleForIndex
import com.una.modus.presentation.common.state.UiState
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusButtonGradientStart
import com.una.modus.ui.theme.ModusPrimaryText
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de "Elegir curso"
 *
 * Paso previo a capturar un apunte (se llega acá desde el botón flotante
 * de cámara): el usuario elige a qué curso pertenece la próxima captura.
 * Reutiliza [GetCursosUseCase] para no duplicar la carga de cursos que ya
 * usan Home y la pestaña Cursos.
 *
 * @param onContinuar callback con el id del curso elegido, invocado al presionar "Continuar".
 * @param onOtroCurso callback invocado al presionar "Otro curso" (agregar uno nuevo).
 */
@Composable
fun ElegirCursoScreen(
    modifier: Modifier = Modifier,
    getCursosUseCase: GetCursosUseCase = remember { GetCursosUseCase(FakeCursoRepository()) },
    onBack: () -> Unit = {},
    onContinuar: (String) -> Unit = {},
    onOtroCurso: () -> Unit = {}
) {
    var state by remember { mutableStateOf<UiState<List<Curso>>>(UiState.Loading) }
    var reloadKey by remember { mutableIntStateOf(0) }
    var selectedCursoId by remember { mutableStateOf<String?>(null) }

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
                .padding(top = 32.dp, bottom = 56.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            BackTopButton(onClick = onBack)

            when (val current = state) {
                is UiState.Loading -> LoadingBlock()
                is UiState.Empty -> EmptyStateView(
                    icon = Icons.Filled.Add,
                    title = "Todavía no tenés cursos",
                    subtitle = "Agregá tu primer curso para empezar a analizar los apuntes de ese profesor.",
                    actionText = "Agregar curso",
                    onAction = onOtroCurso,
                    modifier = Modifier.padding(top = 40.dp)
                )
                is UiState.Error -> ErrorStateView(
                    title = "No pudimos cargar tus cursos",
                    subtitle = current.message,
                    retryText = "Reintentar",
                    onRetry = { reloadKey++ },
                    modifier = Modifier.padding(top = 40.dp)
                )
                is UiState.Success -> {
                    val cursos = current.data
                    val effectiveSelectedId = selectedCursoId ?: cursos.first().id

                    Text(text = "¿A qué curso pertenece?", color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
                    Text(
                        text = "Elegí el curso antes de capturar. Así el apunte se suma al método correcto.",
                        color = ModusTextMuted,
                        fontSize = 12.5.sp
                    )

                    cursos.forEachIndexed { index, curso ->
                        val isSelected = curso.id == effectiveSelectedId
                        Box(
                            modifier = if (isSelected) {
                                Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(18.dp))
                                    .border(2.dp, ModusButtonGradientStart, RoundedCornerShape(18.dp))
                            } else {
                                Modifier.fillMaxWidth()
                            }
                        ) {
                            ListRowCard(
                                title = curso.nombre,
                                subtitle = "${curso.profesor} · ${curso.cantidadApuntes} apuntes",
                                avatarStyle = avatarStyleForIndex(index),
                                avatarLabel = curso.codigo,
                                onClick = { selectedCursoId = curso.id },
                                trailing = if (isSelected) {
                                    { Icon(imageVector = Icons.Filled.Check, contentDescription = "Seleccionado", tint = ModusPrimaryText) }
                                } else {
                                    null
                                }
                            )
                        }
                    }

                    SecondaryButton(text = "Otro curso", onClick = onOtroCurso)
                    PrimaryGradientButton(text = "Continuar", onClick = { onContinuar(effectiveSelectedId) })
                }
            }
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

@Preview(showBackground = true)
@Composable
private fun ElegirCursoScreenPreview() {
    ModusAppTheme {
        ElegirCursoScreen()
    }
}
