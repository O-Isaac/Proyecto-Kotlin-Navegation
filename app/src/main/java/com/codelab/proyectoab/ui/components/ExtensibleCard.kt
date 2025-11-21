package com.codelab.proyectoab.ui.components

import Jugador
import RepositorioJugadores
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import toLocalizedString

@Composable
fun ExtensibleCard(modifier: Modifier = Modifier, jugador: Jugador) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        onClick = { isExpanded = !isExpanded },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .shadow(2.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)) {
                Icon(
                    imageVector = jugador.icono,
                    contentDescription = jugador.nombre + " icono"
                )
                Text(
                    text = jugador.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }

            if (isExpanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Dorsal: ${jugador.dorsal}")
                    Text(text = "Posición: ${jugador.posicion.toLocalizedString()}")
                    Text(text = "Edad: ${jugador.edad}")
                    Text(text = "Altura: ${jugador.altura}")
                    Text(text = "Peso: ${jugador.peso}")
                    Text(text = "Pais: ${jugador.pais}")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExtensibleCardPreview() {
    val jugador: Jugador = RepositorioJugadores.getJugadores().get(0)
    ExtensibleCard(jugador = jugador)
}