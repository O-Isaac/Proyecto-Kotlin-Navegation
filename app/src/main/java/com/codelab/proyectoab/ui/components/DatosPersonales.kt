package com.codelab.proyectoab.ui.components

import Jugador
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.proyectoab.ui.screens.COLOR_TEXTO
import toLocalizedString

@Composable
fun DatosPersonales(jugador: Jugador) {
    Column(verticalArrangement = Arrangement.Center) {
        Text(jugador.nombre, color = COLOR_TEXTO)
        Text(jugador.posicion.toLocalizedString(), color = COLOR_TEXTO)
        Spacer(Modifier.height(4.dp))
        Text("Edad: ${jugador.edad}", color = COLOR_TEXTO)
        Text("Altura: ${jugador.altura} m", color = COLOR_TEXTO)
        Text("Peso: ${jugador.peso} kg", color = COLOR_TEXTO)
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
fun DatosPersonalesPreview(modifier: Modifier = Modifier) {
    val jugador = RepositorioJugadores.getJugadores().get(0)
    DatosPersonales(jugador)
}