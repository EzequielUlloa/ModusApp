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
 * Pantalla de Onboarding · Fotografiá tus apuntes
 *
 * Primera pantalla del onboarding (nodo Figma "19 · Bienvenida 1", sección
 * OSCURO · Onboarding). Es puramente visual: no persiste que el onboarding
 * fue visto ni depende de ninguna capa de dominio/datos todavía.
 *
 * @param onNext se invoca al presionar "Siguiente"; lleva a [OnboardingMethodScreen].
 * @param onSkip se invoca al presionar "Saltar", saltando directo a Login.
 */
@Composable
fun OnboardingPhotoScreen(
    modifier: Modifier = Modifier,
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ModusBackgroundGradient)
            .padding(start = 28.dp, end = 28.dp, top = 82.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Ilustración: formas geométricas abstractas, sin asset externo.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .clip(RoundedCornerShape(28.dp))
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 80.dp, y = 50.dp)
                    .size(width = 150.dp, height = 190.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(ModusIllustrationPrimary)
            )
            Box(
                modifier = Modifier
                    .offset(x = 20.dp, y = 140.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(ModusLink)
            )
            Box(
                modifier = Modifier
                    .offset(x = 180.dp, y = 30.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(ModusIllustrationDeepGreen)
            )
        }
        Text(
            text = "Fotografiá tus apuntes",
            color = ModusText,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Tomá una foto de la pizarra o del cuaderno al terminar la clase. No hace falta que salga perfecta.",
            color = ModusTextMuted,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Medium
        )
        OnboardingDotsIndicator(activeIndex = 0)
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
private fun OnboardingPhotoScreenPreview() {
    ModusAppTheme {
        OnboardingPhotoScreen()
    }
}
