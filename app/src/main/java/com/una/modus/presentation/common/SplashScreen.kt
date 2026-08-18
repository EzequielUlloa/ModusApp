package com.una.modus.presentation.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.R
import com.una.modus.ui.theme.ModusAccentGradient
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusProgressTrack
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de bienvenida (Splash)
 *
 * Primera pantalla que ve el usuario al abrir la app: muestra el logo,
 * el nombre y el eslogan de Modus mientras anima una barra de carga.
 * Al completarse la animación, avanza automáticamente llamando a
 * [onFinished] (el grafo de navegación decide a qué pantalla ir).
 *
 * @param onFinished callback invocado una vez termina la animación de carga.
 */
@Composable
fun SplashScreen(modifier: Modifier = Modifier, onFinished: () -> Unit = {}) {
    // Progreso de la barra de carga, animado de 0 a 1 al entrar a la pantalla.
    val progress = remember { Animatable(0f) }

    // Dispara la animación una sola vez y navega al terminar.
    LaunchedEffect(Unit) {
        progress.animateTo(1f, animationSpec = tween(durationMillis = 1400))
        onFinished()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .padding(horizontal = 28.dp, vertical = 46.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bloque central: logo + nombre + textos descriptivos.
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.modus_logo),
                contentDescription = "Modus",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(162.dp)
            )
            Text(
                text = "Modus",
                color = ModusText,
                fontSize = 42.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 18.dp)
            )
            Text(
                text = "Descubrí el método de cada profesor",
                color = ModusTextMuted,
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 18.dp)
            )
            Text(
                text = "EIF411 · Universidad Nacional",
                color = ModusTextMuted,
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        // Barra de carga: pista fija + relleno cuyo ancho sigue "progress".
        Box(
            modifier = Modifier
                .padding(top = 24.dp)
                .width(120.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(ModusProgressTrack)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = progress.value)
                    .clip(RoundedCornerShape(100.dp))
                    .background(ModusAccentGradient)
            )
        }
    }
}
