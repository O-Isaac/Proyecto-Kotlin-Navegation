package com.codelab.proyectoab.ui.screens

import Jugador
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.codelab.proyectoab.ui.components.DatosPersonales
import com.codelab.proyectoab.ui.components.EscudoYPais
import com.codelab.proyectoab.ui.components.FotoYEstadisticas

val COLOR_ALBACETE = Color(0xFF881C1E) // Rojo del club
val COLOR_FONDO = Color.Black
val COLOR_TEXTO = Color.White

@Composable
fun DetalleJugadorHorizontal(jugador: Jugador) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_FONDO)
    ) {
        Text(
            text = jugador.dorsal.toString(),
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 200.sp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f,
                COLOR_ALBACETE.red,
                COLOR_ALBACETE.green,
                COLOR_ALBACETE.blue
            ),
            modifier = Modifier.zIndex(-1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            DatosPersonales(jugador)
            FotoYEstadisticas(jugador)
            EscudoYPais(jugador)

        }

    }
}

@Composable
@Preview(heightDp = 300, widthDp = 800)
fun DetalleJugadorHorizontalPreview(modifier: Modifier = Modifier) {
    val jugador = RepositorioJugadores.getJugadores().get(0)
    DetalleJugadorHorizontal(jugador)
}