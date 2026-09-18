package com.example.appPruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appPruebas.ui.theme.AppPruebasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPruebasTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var obraActual by remember { mutableIntStateOf(1) }

    val imagen = when (obraActual) {
        1 -> R.drawable._4058158
        2 -> R.drawable._530286248_0
        else -> R.drawable.d9d1a35beab6327a28e929ff20f8a6e7
    }

    val titulo = when (obraActual) {
        1 -> "Lionel Messi"
        2 -> "Dani Alves"
        else -> "Lamine Yamal"
    }

    val artista = when (obraActual) {
        1 -> "Argentina, 2026"
        2 -> "Brasil, 2025"
        else -> "España, 2024"
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val horizontal = maxWidth > maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(if (horizontal) 8.dp else 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ArtworkImage(
                imagen = imagen,
                titulo = titulo,
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier
                    .widthIn(max = 520.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtworkInfo(titulo = titulo, artista = artista)
                ArtworkButtons(
                    onPrevious = { obraActual = if (obraActual == 1) 3 else obraActual - 1 },
                    onNext = { obraActual = if (obraActual == 3) 1 else obraActual + 1 }
                )
            }
        }
    }
}

@Composable
fun ArtworkImage(
    imagen: Int,
    titulo: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imagen),
        contentDescription = titulo,
        contentScale = contentScale,
        modifier = modifier
            .fillMaxSize()
    )
}
@Composable
fun ArtworkInfo(titulo: String, artista: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = titulo, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = artista, fontSize = 14.sp)
    }
}
@Composable
fun ArtworkButtons(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPrevious) {
            Text("Anterior")
        }
        Button(onClick = onNext) {
            Text("Siguiente")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    AppPruebasTheme {
        ArtSpaceApp()
    }
}
