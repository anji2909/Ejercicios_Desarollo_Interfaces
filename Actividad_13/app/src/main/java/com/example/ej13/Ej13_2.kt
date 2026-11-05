package com.example.ej13

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Scaffold
import com.example.ej13.ui.theme.Ej13Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEjercicio2() {
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MI AULA",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Acción */ }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // La tarjeta se mantiene igual, el título ya no está aquí
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Proyecto de interfaz",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Bienvenido a tu espacio de aprendizaje donde podrás consultar tareas y avisos.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Última actualización: hoy",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Button(
                    onClick = { /* Acción */ },
                    modifier = Modifier.fillMaxWidth()) {
                    Text("Comenzar")

                }
            }

        }
    }
}

// Previews para que lo veas al instante sin ir a MainActivity
@Preview(showBackground = true, name = "Tema claro")
@Composable
fun PreviewEj2Claro() {
    Ej13Theme(darkTheme = false) { PantallaEjercicio2() }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Tema oscuro")
@Composable
fun PreviewEj2Oscuro() {
    Ej13Theme(darkTheme = true) { PantallaEjercicio2() }
}