package com.codelab.proyectoab.ui.components

import Jugador
import RepositorioJugadores
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.proyectoab.ui.screens.COLOR_TEXTO

@Composable
fun FotoYEstadisticas(jugador: Jugador) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(jugador.imagenId),
            contentDescription = jugador.urlPerfil,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .clip(CircleShape)
        )

        Text("Partidos: ${jugador.partidos}", color = COLOR_TEXTO)
        Text("Goles: ${jugador.goles}", color = COLOR_TEXTO)
        Text("Asistencias: ${jugador.asistencias}", color = COLOR_TEXTO)
    }

}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
fun FotoYEstadisticasPreview(modifier: Modifier = Modifier) {
    val jugador = RepositorioJugadores.getJugadores().get(0)
    FotoYEstadisticas(jugador)
}