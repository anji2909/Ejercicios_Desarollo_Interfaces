package com.venanciob.dam.pmdm.ej14


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun TarjaPresentacion() {
    var mostrar by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD8EFD7))
            .padding(32.dp)
    ) {
        //parte 1

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.foto
                ),
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(350.dp)
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = "Angelo Baeza",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = { mostrar = !mostrar }) {
                Text(if (mostrar) "OCULTAR" else "MOSTRAR")
            }
            AnimatedVisibility(
                visible = mostrar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            )
            {

                Text(
                    text = "Ing. en Telecomunicaciones y Desarollador ",
                    fontSize = 16.sp,
                    color = Color(0xFF2E7D32)
                )


                // parte 2
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )
                    FilaContacto(
                        icono = "☎",
                        texto = "+34 653 881 193"
                    )
                    FilaContacto(
                        icono = "@",
                        texto = "Angelo_Baeza_"
                    )
                    FilaContacto(
                        icono = "✉",
                        texto = "Angelo.baeza2909@gmail.com"
                    )
                }

            }
        }
    }

}

@Composable
private fun FilaContacto(
    icono: String,
    texto: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icono,
            fontSize = 22.sp,
            color = Color(0xFF006C4C)
        )

        Spacer(
            modifier = Modifier.width(20.dp)
        )

        Text(
            text = texto,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeQuadrantAppPreview() {
}
