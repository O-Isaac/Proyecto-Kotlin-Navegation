package com.codelab.proyectoab.ui.components

import Jugador
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.proyectoab.R
import com.codelab.proyectoab.ui.screens.COLOR_TEXTO

@Composable
fun EscudoYPais(jugador: Jugador) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Pais", color = COLOR_TEXTO)
        Text(jugador.pais, color = COLOR_TEXTO)
        Spacer(Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.albacete_balompie),
            contentDescription = "Logo",
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
        )
    }
}

@Composable
@Preview
fun EscudoYPaisPreview(modifier: Modifier = Modifier) {
    val jugador = RepositorioJugadores.getJugadores().get(0)
    EscudoYPais(jugador)
}