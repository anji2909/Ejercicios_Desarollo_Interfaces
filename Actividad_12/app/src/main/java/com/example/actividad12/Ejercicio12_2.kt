package com.example.actividad12

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actividad12.ui.theme.MiTema

// 1. Datos de las tareas
data class Tarea(
    val titulo: String,
    val descripcion: String
)

val listaTareas = listOf(
    Tarea("Práctica 12", "Listas, Material y animaciones, y mucho mas esto es para poder extender la tarea y que se vea la diferencia "),
    Tarea("Proyecto Figma", "Prototipo de interfaz"),
    Tarea("Repasar Compose", "Text, Row, Column y Box"),
    Tarea("Preparar entrega", "Capturas y documentación")
)

class MainActivityReto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiTema {
                PantallaMiAula()
            }
        }
    }
}

// 2. Pantalla Principal
@Composable
fun PantallaMiAula() {
    var nuevasTareas by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { nuevasTareas++ }) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "MI AULA",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            item { CabeceraPerfil() }
            item { ImagenDestacada() }
            item {
                ZonaAcciones(
                    nuevasTareas = nuevasTareas,
                    onReiniciar = { nuevasTareas = 0 }
                )
            }
            item {
                Text(
                    text = "Nuevas tareas: $nuevasTareas",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            item {
                Text(
                    text = "TAREAS",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            items(listaTareas) { tarea ->
                TarjetaTarea(tarea = tarea)
            }
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

// 3. Zona 1: Cabecera de perfil
@Composable
fun CabeceraPerfil() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(60.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {

                Text(
                    text = "A",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(text = "Angelo Baeza", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "DAM - Desarrollo de Interfaces",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// 4. Zona 2: Imagen destacada con Box
@Composable
fun ImagenDestacada() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = "DESARROLLO DE INTERFACES",
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f))
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

// 5. Zona 3: Botonera completa
@Composable
fun ZonaAcciones(nuevasTareas: Int, onReiniciar: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onReiniciar,
                enabled = nuevasTareas > 0,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("REINICIAR")
            }

            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("GUARDAR")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { }) { Text("MÁS INFO") }
            ElevatedButton(onClick = { }) { Text("EDITAR PERFIL") }
            IconButton(onClick = { }) {
                // Reemplazado Icon por un texto/emoji para mantener el componente
                Text("⚙️")
            }
        }

        FilledTonalButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VER PROGRESO")
        }
    }
}

// 6. Zona 4: Tarjeta interactiva
@Composable
fun TarjetaTarea(tarea: Tarea) {
    var expandida by remember { mutableStateOf(false) }

    val colorFondo by animateColorAsState(
        targetValue = if (expandida)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surface,
        label = "colorTarea"
    )

    Surface(
        color = colorFondo,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { expandida = !expandida }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tarea.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = tarea.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = if (expandida) Int.MAX_VALUE else 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMiAulaClaro() {
    MiTema {
        PantallaMiAula()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewMiAulaOscuro() {
    MiTema {
        PantallaMiAula()
    }
}