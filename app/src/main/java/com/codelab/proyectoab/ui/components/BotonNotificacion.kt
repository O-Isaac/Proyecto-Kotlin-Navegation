package com.codelab.proyectoab.ui.components

import Jugador
import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.codelab.proyectoab.MainActivity
import com.codelab.proyectoab.utils.PermissionStore
import com.codelab.proyectoab.utils.putCode
import com.codelab.proyectoab.utils.putMessage
import com.codelab.proyectoab.utils.putTitle

fun notificarCallback(jugador: Jugador, context: Context, mainActivity: MainActivity, permissionStore: PermissionStore) {
    if (permissionStore.notificationPermission.hasPermission(context)) {
        mainActivity.notificationChannelStore.canalJugadores.notify {
            putTitle("¡Logro del dia!")
            putMessage("${jugador.nombre} ha marcado su tercer gol consecutivo")
            putCode(jugador.id)
            putInt(MainActivity.EXTRA_JUGADOR_ID, jugador.id)
        }
    } else {
        permissionStore.notificationPermission.request();
    }
}

@Composable
fun BotonNotificacion(jugador: Jugador) {
    val context = LocalContext.current
    val mainActivity = context as MainActivity
    val permissionStore = mainActivity.permissionStore

    Button(
        onClick = { notificarCallback(jugador, context, mainActivity, permissionStore) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Notificar Logro")
    }
}