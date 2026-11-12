package com.example.actividad12


import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

// Asegúrate de que estos imports coincidan con la ruta de tu proyecto
import com.example.actividad12.ui.theme.MiTema
data class Mensaje(
    val autor: String,
    val texto: String,
    val imagenRes: Int
)

val misMensajes = listOf(
    Mensaje("Anji", "¿Ya tienen listo el nuevo diseño de la web?", R.drawable.avatar_anji),
    Mensaje("Mary", "Sí, los storyboards quedaron geniales.", R.drawable.avatar_mary),
    Mensaje("Marta", "¡Mañana probamos las nuevas recetas de gelato!", R.drawable.avatar_marta),
    Mensaje("Javi", "Nos vemos mañana para revisar la base de datos del TPV.", R.drawable.avatar_javi),
    Mensaje("Ari", "He subido el código al repositorio.", R.drawable.avatar_ari)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {



        // Ejercicio 1 Descomentar y comentar el 1 y el 2 para ver la diferencia
            var isDarkMode by remember { mutableStateOf(false) }
            MiTema(darkTheme = isDarkMode) {
              PantallaMensajes(
                   onThemeToggle = { isDarkMode = !isDarkMode })
                   }


        /* Ejercicio 2 Descomentar y comentar el 1 y el 2 para ver la diferencia */
            /*val isDarkMode = isSystemInDarkTheme()
            MiTema(darkTheme = isDarkMode) {
                PantallaMiAula()
            }
            */
        }
    }
}

@Composable
fun PantallaMensajes(onThemeToggle: () -> Unit) {
    // 3. Usamos Scaffold para estructurar la pantalla y colocar el botón flotante
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onThemeToggle) {
                // Usamos un texto simple en lugar de un Icon
                Text("Modo", modifier = Modifier.padding(8.dp))
            }
        }
    ) { paddingValues ->
        // 4. Aplicamos el paddingValues del Scaffold a la columna principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "MIS MENSAJES",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp)
            )

            LazyColumn {
                items(misMensajes) { mensaje ->
                    TarjetaMensaje(mensaje = mensaje)
                }
            }
        }
    }
}


@Composable
fun TarjetaMensaje(mensaje: Mensaje) {
    var expandido by remember { mutableStateOf(false) }

    // 1. Animación del color de fondo
    val colorFondo by animateColorAsState(
        targetValue = if (expandido) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        label = "colorMensaje"
    )

    // 2. Animación del tamaño de la imagen
    val tamanoAvatar by animateDpAsState(
        targetValue = if (expandido) 80.dp else 64.dp, // Crece al expandir
        label = "tamanoAvatar"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expandido = !expandido } // Ahora toda la fila es clickeable
            .animateContentSize( // Hace que el crecimiento del contenedor sea fluido
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = mensaje.imagenRes),
            contentDescription = "Avatar de ${mensaje.autor}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(tamanoAvatar) // Aplicamos el tamaño animado aquí
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = mensaje.autor,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = colorFondo
            ) {
                Text(
                    text = mensaje.texto,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(12.dp),
                    maxLines = if (expandido) Int.MAX_VALUE else 1
                )
            }
        }
    }
}