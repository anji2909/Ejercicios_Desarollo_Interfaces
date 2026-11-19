package com.venanciob.dam.pmdm.actividad_16

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.actividad_16.ui.theme.AnjiTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

// --- MODELO Y DATOS PARA EJERCICIO 2 ---

data class Ciudad(
    val nombre: String,
    val region: String
)

private val ciudadesEjemplo = listOf(
    Ciudad("Salamanca", "Castilla y León"),
    Ciudad("Salamanca de Tormes", "Castilla y León"),
    Ciudad("Salamanca del Valle", "Castilla y León"),
    Ciudad("Salou", "Cataluña"),
    Ciudad("Santiago de Compostela", "Galicia"),
    Ciudad("Santander", "Cantabria"),
    Ciudad("Burgos", "Castilla y León"),
    Ciudad("Oviedo", "Asturias"),
    Ciudad("Comillas", "Cantabria")
)

// --- STATE HOLDER (EJERCICIO 2) ---

class SearchState(initialText: String = "") {
    var text by mutableStateOf(initialText)
        private set

    fun updateText(newText: String) {
        text = newText
    }

    val isEmpty: Boolean
        get() = text.isBlank()

    companion object {
        val Saver = Saver<SearchState, String>(
            save = { it.text },
            restore = { SearchState(it) }
        )
    }
}

@Composable
fun rememberSearchState(): SearchState =
    rememberSaveable(saver = SearchState.Saver) {
        SearchState()
    }

// --- EJERCICIO 1 + NAVEGACIÓN HACIA EJERCICIO 2 ---

@Composable
fun Ejericio1App() {
    var showLanding by rememberSaveable { mutableStateOf(true) }
    var irABuscador by rememberSaveable { mutableStateOf(false) }

    if (showLanding) {
        LandingScreen(
            onTimeout = { showLanding = false }
        )
    }

    AnimatedVisibility(visible = !showLanding && !irABuscador) {
        ContenidoPrincipal(
            onEntrarClick = { irABuscador = true }
        )
    }

    AnimatedVisibility(visible = !showLanding && irABuscador) {
        PantallaBuscador()
    }
}

@Composable
fun LandingScreen(onTimeout: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(Unit) {
        delay(2000)
        currentOnTimeout()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(400.dp),
                contentDescription = "imagen de carga",
                painter = painterResource(R.drawable.anjilogo)
            )
            Spacer(Modifier.height(5.dp))
            CircularProgressIndicator()
            Text("Cargando...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun ContenidoPrincipal(
    onEntrarClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Bienvenido/A", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(16.dp))
            Button(onClick = onEntrarClick) {
                Text("ENTRAR EN LA APP")
            }
        }
    }
}

// --- EJERCICIO 2: BUSCADOR REUTILIZABLE Y PERSISTENTE ---

@Composable
fun SearchField(
    state: SearchState,
    modifier: Modifier = Modifier
) {
    TextField(
        value = state.text,
        onValueChange = { state.updateText(it) },
        label = { Text("Buscar...") },
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun BarraBuscador(
    state: SearchState,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(state) {
        snapshotFlow { state.text }
            .filter { it.length >= 2 } // evita búsquedas cortas[cite: 1]
            .distinctUntilChanged()
            .collect { onSearch(it) }
    }

    val mostrarLimpiar by remember(state) {
        derivedStateOf { !state.isEmpty }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchField(
            state = state,
            modifier = Modifier.weight(1f)
        )

        if (mostrarLimpiar) {
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { state.updateText("") }) {
                Text("LIMPIAR")
            }
        }
    }
}

@Composable
fun CiudadItem(ciudad: Ciudad, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = ciudad.nombre,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = ciudad.region,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun ResultadosCiudades(
    ciudades: List<Ciudad>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(ciudades, key = { "${it.nombre}-${it.region}" }) { ciudad ->
            CiudadItem(ciudad = ciudad)
        }
    }
}

@Composable
fun PantallaBuscador(
    ciudades: List<Ciudad> = ciudadesEjemplo,
    modifier: Modifier = Modifier
) {
    val state = rememberSearchState()
    var terminoBusqueda by rememberSaveable { mutableStateOf("") }

    val ciudadesFiltradas by remember(terminoBusqueda, ciudades) {
        derivedStateOf {
            if (terminoBusqueda.length < 2) {
                emptyList()
            } else {
                ciudades.filter {
                    it.nombre.contains(terminoBusqueda, ignoreCase = true) ||
                            it.region.contains(terminoBusqueda, ignoreCase = true)
                }
            }
        }
    }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Buscador de ciudades",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(12.dp))

            BarraBuscador(
                state = state,
                onSearch = { termino ->
                    terminoBusqueda = termino
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            when {
                state.text.length < 2 -> {
                    Text(
                        text = "Escribe al menos 2 caracteres para buscar.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                ciudadesFiltradas.isEmpty() -> {
                    Text(
                        text = "No se encontraron resultados para \"${state.text}\".",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                else -> {
                    Text(
                        text = "Resultados (${ciudadesFiltradas.size})",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ResultadosCiudades(ciudades = ciudadesFiltradas)
                }
            }
        }
    }
}

// --- PREVIEWS ---

@Preview(name = "Claro", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PantallaBuscadorPreview() {
    AnjiTheme {
        PantallaBuscador()
    }
}