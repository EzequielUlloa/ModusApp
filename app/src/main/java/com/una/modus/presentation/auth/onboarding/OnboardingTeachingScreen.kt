package com.una.modus.presentation.auth.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusIllustrationDeepGreen
import com.una.modus.ui.theme.ModusIllustrationPrimary
import com.una.modus.ui.theme.ModusLink
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Onboarding · Estudiá como enseña
 *
 * Tercera y última pantalla del onboarding (nodo Figma "21 · Bienvenida 3",
 * sección OSCURO · Onboarding), mostrada después de [OnboardingMethodScreen].
 * Es puramente visual: no depende de ninguna capa de dominio/datos todavía.
 * A diferencia de las dos anteriores, no tiene "Saltar" porque ya es el
 * último paso del flujo.
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onStart se invoca al presionar "Comenzar"; termina el onboarding.
 */
@Composable
fun OnboardingTeachingScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onStart: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .padding(start = 28.dp, end = 28.dp, top = 82.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        BackTopButton(onClick = onBack)
        // Ilustración: formas geométricas abstractas, sin asset externo.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .clip(RoundedCornerShape(28.dp))
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 20.dp, y = 20.dp)
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(ModusLink)
            )
            Box(
                modifier = Modifier
                    .offset(x = 120.dp, y = 120.dp)
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(ModusIllustrationPrimary)
            )
            Box(
                modifier = Modifier
                    .offset(x = 220.dp, y = 60.dp)
                    .size(width = 70.dp, height = 180.dp)
                    .clip(RoundedCornerShape(34.dp))
                    .background(ModusIllustrationDeepGreen.copy(alpha = 0.85f))
            )
        }
        Text(
            text = "Estudiá como enseña",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Cada profesor tiene un método. Conocerlo te ahorra tiempo cuando estudiás para el examen.",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        OnboardingDotsIndicator(activeIndex = 2)
        PrimaryGradientButton(text = "Comenzar", onClick = onStart)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingTeachingScreenPreview() {
    ModusAppTheme {
        OnboardingTeachingScreen()
    }
}
