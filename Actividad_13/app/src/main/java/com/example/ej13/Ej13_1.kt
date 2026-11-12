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
import com.example.ej13.ui.theme.AnjiTheme


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

@Preview(name = "Claro", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MiComponentePreview() {
    AnjiTheme() {
        PantallaEjercicio1()
    }

}