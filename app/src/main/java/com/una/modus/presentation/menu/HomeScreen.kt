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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BottomNavDestination
import com.una.modus.presentation.common.components.CourseProgressRing
import com.una.modus.presentation.common.components.ListRowCard
import com.una.modus.presentation.common.components.ModusBottomNavBar
import com.una.modus.ui.theme.AvatarStyle
import com.una.modus.ui.theme.ModusAvatarBlue
import com.una.modus.ui.theme.ModusAvatarOrange
import com.una.modus.ui.theme.ModusAvatarTeal
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusButtonGradient
import com.una.modus.ui.theme.ModusOnPrimary
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/** Curso de muestra mostrado en "Tus cursos" del Home. */
private data class HomeCourse(
    val code: String,
    val name: String,
    val professor: String,
    val notesCount: Int,
    val progress: Int,
    val avatarStyle: AvatarStyle
)

/**
 * Pantalla de inicio (Home)
 *
 * Destino al que llega el usuario tras completar el login o el registro.
 * Muestra el saludo, el acceso directo a "Analizar un apunte nuevo" y la
 * lista de cursos activos, con la barra de navegación inferior fija.
 *
 * Los cursos son datos de muestra: la capa real (GetCursosUseCase) es
 * responsabilidad del Frente 3 y debe reemplazar este `remember` cuando
 * exista.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userName: String = "Josué",
    onNavigateCursos: () -> Unit = {},
    onNavigateAvisos: () -> Unit = {},
    onNavigatePerfil: () -> Unit = {},
    onCapturar: () -> Unit = {},
    onCourseClick: (String) -> Unit = {}
) {
    val courses = remember {
        listOf(
            HomeCourse("CI", "Cálculo I", "Prof. R. León", 12, 78, ModusAvatarTeal),
            HomeCourse("FG", "Física General", "Prof. M. Guzmán", 8, 64, ModusAvatarBlue),
            HomeCourse("BD", "Bases de Datos", "Prof. A. Solano", 5, 41, ModusAvatarOrange)
        )
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
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(text = "Hola, $userName", color = ModusText, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "II Ciclo 2026 · ${courses.size} cursos activos",
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

            courses.forEach { course ->
                ListRowCard(
                    title = course.name,
                    subtitle = "${course.professor} · ${course.notesCount} apuntes",
                    avatarStyle = course.avatarStyle,
                    avatarLabel = course.code,
                    onClick = { onCourseClick(course.code) },
                    trailing = { CourseProgressRing(progress = course.progress) }
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
