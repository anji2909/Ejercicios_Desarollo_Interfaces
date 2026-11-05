package com.example.ej13

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ej13.ui.theme.Ej13Theme
import com.tuusuario.practica13.PantallaEjercicio1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ej13Theme {
                //PantallaEjercicio1()
                //PantallaEjercicio2()
                //PantallaEjercicio3()
                PantallaProyectoFinal()
            }
        }
    }
}

