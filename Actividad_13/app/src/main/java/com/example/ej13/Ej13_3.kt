package com.example.ej13

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ej13.ui.theme.Ej13Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEjercicio3() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("MI AULA", style = MaterialTheme.typography.headlineSmall)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tarjeta principal con MaterialTheme.shapes.medium
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium // Aplicando la forma
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Título
                    Text(
                        text = "Material 3 en Jetpack Compose",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Cuerpo
                    Text(
                        text = "Hemos integrado color, tipografía y formas en un solo tema coherente. La interfaz ahora se ve uniforme.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Texto secundario con menor enfasis
                    Text(
                        text = "Práctica 13 completada",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Zona de botonees
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
            ) {
                // Acción secundaria (menor protagonismo)
                OutlinedButton(onClick = { /* Acción */ }) {
                    Text("MÁS INFO")
                }

                // Botón principal (usa primary automáticamente)
                Button(onClick = { /* Acción */ }) {
                    Text("GUARDAR")
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Tema claro")
@Composable
fun PreviewEj3Claro() {
    Ej13Theme(darkTheme = false) { PantallaEjercicio3() }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Tema oscuro")
@Composable
fun PreviewEj3Oscuro() {
    Ej13Theme(darkTheme = true) { PantallaEjercicio3() }
}