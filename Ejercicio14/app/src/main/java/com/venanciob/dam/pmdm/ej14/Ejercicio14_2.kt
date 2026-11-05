package com.venanciob.dam.pmdm.ej14

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.ej14.ui.theme.Ej14Theme

enum class EstadoEntrenamiento {
    Normal,
    Activa
}

@Composable
fun PantallaEntrenamiento() {


    var estado by remember {
        mutableStateOf(EstadoEntrenamiento.Normal)
    }

    val transition = updateTransition(
        targetState = estado,
        label = "Entrenamiento"
    )

    val color by transition.animateColor(
        label = "color"
    ) { s ->
        if (s == EstadoEntrenamiento.Normal)
            MaterialTheme.colorScheme.surface
        else
            MaterialTheme.colorScheme.primaryContainer
    }

    val ancho by transition.animateDp(
        label = "ancho"
    ) { s ->
        if (s == EstadoEntrenamiento.Normal) 220.dp else 320.dp
    }

    // Textos según el estado
    val texto = if (estado == EstadoEntrenamiento.Normal) "DESCANSANDO" else "ENTRENANDO"
    val textoBoton = if (estado == EstadoEntrenamiento.Normal) "INICIAR ENTRENAMIENTO" else "FINALIZAR ENTRENAMIENTO"

    // 2. Corregido: Modifier de Compose y centrado vertical
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Círculo superior con el icono de entrenamiento (opcional si tienes un drawable propio)
        Surface(
            modifier = Modifier.size(220.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(painter=painterResource(id=R.drawable.foto),
                    contentDescription = "Foto de perfil"
                   )


            }
        }

        Spacer(Modifier.height(24.dp))

        // 3. Corregido: el Surface se cierra justo después del Box
        Surface(
            color = color,
            shape = RoundedCornerShape(14.dp),
            shadowElevation = 4.dp
        ) {
            Box(
                modifier = Modifier
                    .width(ancho)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = texto,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        } // <-- Aquí termina la tarjeta (Surface)

        Spacer(Modifier.height(24.dp))

        // 4. Corregido: sintaxis del onClick y añadido el Text del botón
        Button(
            onClick = {
                estado = if (estado == EstadoEntrenamiento.Normal)
                    EstadoEntrenamiento.Activa
                else
                    EstadoEntrenamiento.Normal
            }
        ) {
            Text(text = textoBoton)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaEntrenamientoPreview() {
    Ej14Theme {
        PantallaEntrenamiento()
    }
}