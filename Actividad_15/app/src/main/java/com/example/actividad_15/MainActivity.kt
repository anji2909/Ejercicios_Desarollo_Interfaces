package com.example.actividad_15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SelectorPractica15()
            }
        }
    }
}

/**
 * Selector auxiliar para probar los 3 apartados y hacer las capturas de pantalla.
 */
@Composable
fun SelectorPractica15() {
    var pestana by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = pestana) {
            Tab(selected = pestana == 0, onClick = { pestana = 0 }, text = { Text("Ej 1A") })
            Tab(selected = pestana == 1, onClick = { pestana = 1 }, text = { Text("Ej 1B") })
            Tab(selected = pestana == 2, onClick = { pestana = 2 }, text = { Text("Ej 2: Mi Aula") })
        }

        Box(modifier = Modifier.weight(1f)) {
            when (pestana) {
                0 -> ModoEstudioMaterial()
                1 -> ModoEstudioPersonalizado()
                2 -> AppMiAula()
            }
        }
    }
}

// =========================================================================
// EJERCICIO 1 - PARTE A: VERSIÓN MATERIAL (Nivel alto)
// =========================================================================

@Composable
fun ModoEstudioMaterial() {
    var activo by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MODO ESTUDIO",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Activa un entorno sin distracciones para concentrarte.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { activo = !activo }) {
                Text(if (activo) "DESACTIVAR" else "ACTIVAR")
            }

            AnimatedVisibility(visible = activo) {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Modo estudio activo",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// =========================================================================
// EJERCICIO 1 - PARTE B: VERSIÓN PERSONALIZADA (Nivel más bajo)
// =========================================================================

@Composable
fun ModoEstudioPersonalizado() {
    var activo by remember { mutableStateOf(false) }

    val colorFondo by animateColorAsState(
        targetValue = if (activo) Color(0xFF4CAF50) else Color(0xFF6750A4),
        label = "colorBoton"
    )
    val escala by animateFloatAsState(
        targetValue = if (activo) 1.05f else 1f,
        label = "escalaBoton"
    )

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MODO ESTUDIO",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Activa un entorno sin distracciones para concentrarte.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sustituimos Button por Row + Modifier.clickable() + fondo degradado propio
            Row(
                modifier = Modifier
                    .scale(escala)
                    .clip(RoundedCornerShape(50.dp))
                    .clickable { activo = !activo }
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(colorFondo, colorFondo.copy(alpha = 0.75f))
                        ),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(horizontal = 32.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (activo) "DESACTIVAR" else "ACTIVAR",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            AnimatedVisibility(visible = activo) {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Modo estudio activo",
                        color = Color(0xFF2E7D32),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// =========================================================================
// EJERCICIO 2: MINI APP «MI AULA»
// =========================================================================

// Modelo de datos
data class Tarea(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val completada: Boolean
)

val tareasIniciales = listOf(
    Tarea("13", "Práctica 13", "Material 3", true),
    Tarea("14", "Práctica 14", "Animaciones", true),
    Tarea("15", "Práctica 15", "Arquitectura de Compose", false)
)

// Claves de navegación (Navigation 3)
private data object Lista
private data class Detalle(val id: String)

@Composable
fun AppMiAula() {
    // Estado de las tareas para que el cambio completada/pendiente se refleje en toda la app
    val listaTareas = remember { mutableStateListOf(*tareasIniciales.toTypedArray()) }

    // Pila de navegación (Back stack)
    val backStack = remember { mutableStateListOf<Any>(Lista) }

    // Destino actual (último elemento de la pila)
    val destinoActual = backStack.lastOrNull() ?: Lista

    when (destinoActual) {
        is Lista -> {
            PantallaLista(
                tareas = listaTareas,
                onAbrir = { id -> backStack.add(Detalle(id)) }
            )
        }
        is Detalle -> {
            val indice = listaTareas.indexOfFirst { it.id == destinoActual.id }
            val tarea = listaTareas.getOrNull(indice)
            if (tarea != null) {
                PantallaDetalle(
                    tarea = tarea,
                    onCambiarEstado = {
                        listaTareas[indice] = tarea.copy(completada = !tarea.completada)
                    },
                    onAtras = {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLista(
    tareas: List<Tarea>,
    onAbrir: (String) -> Unit
) {
    var mostrarAviso by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Aula", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarAviso = !mostrarAviso },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir tarea")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tareas, key = { it.id }) { tarea ->
                    TarjetaTarea(
                        tarea = tarea,
                        onAbrir = { onAbrir(tarea.id) }
                    )
                }
            }

            AnimatedVisibility(visible = mostrarAviso) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Añadir tarea",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaTarea(
    tarea: Tarea,
    onAbrir: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAbrir() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (tarea.completada) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Completada",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(28.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tarea.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = tarea.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Abrir detalle"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(
    tarea: Tarea,
    onCambiarEstado: () -> Unit,
    onAtras: () -> Unit
) {
    // Animación de color según el estado completada/pendiente
    val colorTarjeta by animateColorAsState(
        targetValue = if (tarea.completada) Color(0xFFE8F5E9) else Color(0xFFFFF3E0),
        label = "colorTarjetaDetalle"
    )
    val colorBorde by animateColorAsState(
        targetValue = if (tarea.completada) Color(0xFF4CAF50) else Color(0xFFFF9800),
        label = "colorBordeDetalle"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de tarea") },
                navigationIcon = {
                    IconButton(onClick = onAtras) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = tarea.titulo,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = tarea.descripcion,
                style = MaterialTheme.typography.bodyLarge
            )

            // Tarjeta con cambio animado de color y tamaño
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                shape = RoundedCornerShape(16.dp),
                color = colorTarjeta,
                border = BorderStroke(1.5.dp, colorBorde)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (tarea.completada) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xFF2E7D32)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .border(2.dp, Color(0xFFE65100), CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = if (tarea.completada) "Completada" else "Pendiente",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = if (tarea.completada) Color(0xFF2E7D32) else Color(0xFFE65100)
                    )
                }
            }

            Button(
                onClick = onCambiarEstado,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (tarea.completada) Icons.Default.Refresh else Icons.Default.Check,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (tarea.completada) "MARCAR PENDIENTE" else "MARCAR COMPLETADA")
            }

            OutlinedButton(
                onClick = onAtras,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ATRÁS")
            }
        }
    }
}