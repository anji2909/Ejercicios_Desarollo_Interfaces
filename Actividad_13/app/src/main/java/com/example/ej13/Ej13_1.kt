package com.tuusuario.practica13

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ej13.ui.theme.Ej13Theme

@Composable
fun PantallaEjercicio1() {

    Scaffold(
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "MI AULA")

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Avisos")
                    Text(text = "Entrega de práctica el viernes")
                }
            }

            Button(onClick = { /* Acción */ }) {
                Text("Entrar →")
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Tema claro"
)
@Composable
fun PreviewClaro() {
    Ej13Theme(darkTheme = false) {
        PantallaEjercicio1()
    }
}@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Tema oscuro"
)
@Composable
fun PreviewOscuro() {
    Ej13Theme(darkTheme = true) {
        PantallaEjercicio1()
    }
}