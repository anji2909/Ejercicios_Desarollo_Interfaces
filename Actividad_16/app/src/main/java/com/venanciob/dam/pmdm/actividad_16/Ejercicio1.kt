package com.venanciob.dam.pmdm.actividad_16

import android.R
import android.content.res.Configuration
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.actividad_16.ui.theme.AnjiTheme
import kotlinx.coroutines.delay


@Composable
fun Ejericio1App() {

    var showLanding by remember { mutableStateOf(true) }

    if(showLanding){
        LandingScreen(
            onTimeout = { showLanding = false }
        )
    }
    AnimatedVisibility(visible = !showLanding) {
        ContenidoPrincipal()
    }

}

@Composable
fun LandingScreen(onTimeout:() -> Unit){
    // mantiene la version mas reciente de la callback
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    // lanza una corrutina al entrar en la composisicon
    LaunchedEffect(Unit){
        delay(2000)  // espera 2 seg
        currentOnTimeout()  // ejecuta la call back actual
    }

    // contenido visual (ejemplo)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment= Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Mi AULA", style = MaterialTheme.typography.headlineLarge)
//            Image(
//                modifier = Modifier.size(150.dp),
//                contentDescription ="imagen de carga",
//                painter = painterResource(R.drawable.btn_star)
//            )
            Spacer(Modifier.height(16.dp))
            CircularProgressIndicator()
            Text("Cargando...", style = MaterialTheme.typography.bodyLarge)

        }
    }

}

@Composable
fun ContenidoPrincipal(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment= Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Bienvenido/A ", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(16.dp))
            Button(onClick = { /* Acción o reiniciar */ }){
                Text("ENTRAR EN LA APP")
            }

        }
    }
}

@Preview(name = "Claro", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MiComponentePreview2() {
    AnjiTheme() {
        ContenidoPrincipal()
    }
}
