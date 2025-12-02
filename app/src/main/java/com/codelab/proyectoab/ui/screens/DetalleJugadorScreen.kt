package com.codelab.proyectoab.ui.screens

import Jugador
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun DetalleJugadorScreen(jugador: Jugador) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        DetalleJugadorHorizontal(jugador)
    } else {
        DetalleJugadorVertical(jugador)
    }
}