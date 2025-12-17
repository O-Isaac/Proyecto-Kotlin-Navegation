package com.codelab.proyectoab.ui.components

import Jugador
import RepositorioJugadores
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import toLocalizedString

@Composable
fun ExtensibleCard(
    jugador: Jugador,
    isExpanded: Boolean,
    onExpandedChange: () -> Unit,
    onClickPerfil: (String) -> Unit
) {
    Card(
        onClick = { onExpandedChange() },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(1.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(jugador.imagenId),
                    contentDescription = jugador.nombre + " imagen",
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                        .clip(CircleShape)
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

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = { onClickPerfil("detalle_jugador/${jugador.id}") }) {
                            Text("Ver ficha")
                        }
                    }
                }
            }
        }
    }
}