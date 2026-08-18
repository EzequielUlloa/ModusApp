package com.una.modus.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.una.modus.ui.theme.ModusSurface
import com.una.modus.ui.theme.ModusText
import com.una.modus.ui.theme.ModusTextMuted

/**
 * Campo de formulario de autenticación
 *
 * Campo de texto de marca (label arriba + caja redondeada) reutilizado en
 * todos los formularios de Login, Registro y recuperación de contraseña.
 * Cuando [isPassword] es `true`, agrega un ícono de ojo para alternar la
 * visibilidad del texto ingresado (estado manejado internamente).
 *
 * @param label texto de la etiqueta mostrada arriba del campo.
 * @param value valor actual del campo (estado elevado al llamador).
 * @param onValueChange callback invocado con el nuevo valor al escribir.
 * @param placeholder texto de ejemplo mostrado cuando [value] está vacío.
 * @param isPassword si es `true`, oculta el texto y muestra el botón de mostrar/ocultar.
 * @param keyboardType tipo de teclado a mostrar (texto, correo, etc.).
 */
@Composable
fun AuthTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    // Visibilidad de la contraseña: solo aplica si isPassword = true.
    var isVisible by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = label, color = ModusTextMuted, fontSize = 12.5.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .background(ModusSurface, RoundedCornerShape(16.dp))
                .padding(horizontal = 18.dp, vertical = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = ModusText, fontSize = 14.5.sp),
                cursorBrush = SolidColor(ModusText),
                visualTransformation = if (isPassword && !isVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                // Placeholder dibujado a mano: BasicTextField no lo trae incluido.
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(text = placeholder, color = ModusTextMuted, fontSize = 14.5.sp)
                    }
                    innerTextField()
                }
            )
            if (isPassword) {
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(
                        imageVector = if (isVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        tint = ModusTextMuted
                    )
                }
            }
        }
    }
}

