package com.venanciob.dam.pmdm.examen_ab

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.examen_ab.ui.theme.AnjiTheme


data class Libro(
    val titulo: String,
    val autor: String,
    val descripcion: String
)

val listaLibros = mutableStateListOf<Libro>(
    Libro(
        "El principito",
        "Antonie de Saint-",
        "este es el libro mas famoso de francia habla del principito y muchas cosas extras"
    ),
    Libro(
        "Dune",
        "Frank Herbert",
        "Libro de una famosa pelicula de ciencia ficcion en un mundo desertico y tecnologia avanzada"
    ),
    Libro(
        "El hobbit",
        "J.R.R.Token",
        "Libro de fantasia que cuenta la hisotria de la tierra media donde un hobbit cruza para destruir el anillo"
    ),
    Libro(
        "Java desde 0",
        "Alguien con mucho tiempo",
        "Libro para aprender a programar java dedsde 0 hasta avanzado"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnjiTheme() {
                PantallaBibloteca()
            }
        }
    }
}

@Composable
fun PantallaBibloteca() {

    Column(modifier = Modifier.fillMaxSize()) {
        ->


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.avatar_anji),
                contentDescription = "Imagen de la bibloteca",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp) //
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "MI BIBLOTECA",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = "coleccion de libros de angelo",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }



        Spacer(modifier = Modifier.padding(6.dp))

        // Aqui va el contador
        contador()

        // aqui va la tarjeta de libros con lazy colum

        LazyColumn{
            items(listaLibros){ libro ->
            TarjetaLibro(libro)
            }
        }


    }
}

@Composable
fun contador() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        var contador by remember { mutableStateOf(0) }
        Text(
            text = "Pulsaciones : $contador"
        )
        Button(onClick = { contador++ }) {
            Text("Sumar +1")
        }
        OutlinedButton(onClick = { contador = 0 },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )) {
            Text("REINICIAR")


        }

    }

}

@Composable
fun TarjetaLibro(libro:Libro){

    var expandido by remember { mutableStateOf(false) }
    var destacado by remember { mutableStateOf(false) } // Nuevo estado para destacado

    // 1. Animación del color de
    val colorFondo by animateColorAsState(
        targetValue = if (destacado) { // Depende de destacado en lugar de expandido
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        label = "colorMensaje"
    )

    val ancho by animateDpAsState(
        targetValue = if(destacado){180.dp}else{120.dp}
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize( // Hace que el crecimiento del contenedor sea fluido
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = colorFondo,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { expandido = !expandido }, // El click en el Surface expande el texto
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = libro.titulo,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = libro.descripcion,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = if (expandido) Int.MAX_VALUE else 1,
                        )
                    }
                    Button(
                        onClick = {
                            destacado = !destacado // El botón cambia el color de fondo
                        },
                        modifier = Modifier.width(ancho)
                    ) {
                        Text("Destacado")
                    }
                }
            }
        }
    }
}




@Preview(name = "Claro", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MiComponentePreview() {
    AnjiTheme() {
        PantallaBibloteca()
    }

}

