package com.codelab.proyectoab.utils

import com.codelab.proyectoab.MainActivity

class NotificationChannelStore {
    private val channels = listOf(
        NotificationChannelHelper(
            "CANAL_JUGADORES",
            "Eventos del Albacete Balompié",
            "Notificaciones sobre goles, lesiones y logros de los jugadores"
        )
    )

    val canalJugadores get() = channels[0]

    fun register(mainActivity: MainActivity) {
        channels.forEach { it.register(mainActivity) }
    }
}