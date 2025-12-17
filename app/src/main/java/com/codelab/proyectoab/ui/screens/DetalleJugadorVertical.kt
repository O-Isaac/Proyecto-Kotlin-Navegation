package com.codelab.proyectoab.ui.screens

import Jugador
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.codelab.proyectoab.ui.components.BotonNotificacion
import com.codelab.proyectoab.ui.components.DatosPersonales
import com.codelab.proyectoab.ui.components.FotoYEstadisticas

@Composable
fun DetalleJugadorVertical(jugador: Jugador) {
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            FotoYEstadisticas(jugador)
            Spacer(Modifier.height(80.dp))
            DatosPersonales(jugador)
            Spacer(Modifier.height(80.dp))
            BotonNotificacion(jugador)
        }
    }
}

