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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.presentation.common.components.BackTopButton
import com.una.modus.presentation.common.components.LinkText
import com.una.modus.presentation.common.components.PrimaryGradientButton
import com.una.modus.ui.theme.ModusAppTheme
import com.una.modus.ui.theme.ModusBackgroundGradient
import com.una.modus.ui.theme.ModusIllustrationDeepGreen
import com.una.modus.ui.theme.ModusIllustrationPrimary
import com.una.modus.ui.theme.ModusLink
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Pantalla de Onboarding · Modus lee el método
 *
 * Segunda pantalla del onboarding (nodo Figma "20 · Bienvenida 2", sección
 * OSCURO · Onboarding), mostrada después de [OnboardingPhotoScreen]. Es
 * puramente visual: no depende de ninguna capa de dominio/datos todavía.
 *
 * @param onBack se invoca al presionar el botón de volver.
 * @param onNext se invoca al presionar "Siguiente"; lleva a [OnboardingTeachingScreen].
 * @param onSkip se invoca al presionar "Saltar", saltando directo a Login.
 */
@Composable
fun OnboardingMethodScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .padding(start = 28.dp, end = 28.dp, top = 52.dp, bottom = 40.dp),
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
                    .offset(x = 50.dp, y = 40.dp)
                    .size(210.dp)
                    .clip(CircleShape)
                    .background(ModusIllustrationPrimary)
            )
            Box(
                modifier = Modifier
                    .offset(x = 30.dp, y = 150.dp)
                    .size(120.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(ModusLink)
            )
            Box(
                modifier = Modifier
                    .offset(x = 200.dp, y = 190.dp)
                    .size(60.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(ModusIllustrationDeepGreen.copy(alpha = 0.9f))
            )
        }
        Text(
            text = "Modus lee el método",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Detecta el orden de los pasos, la notación propia del profesor y los temas que más repite.",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        OnboardingDotsIndicator(activeIndex = 1)
        PrimaryGradientButton(text = "Siguiente", onClick = onNext)
        LinkText(
            text = "Saltar",
            onClick = onSkip,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingMethodScreenPreview() {
    ModusAppTheme {
        OnboardingMethodScreen()
    }
}
