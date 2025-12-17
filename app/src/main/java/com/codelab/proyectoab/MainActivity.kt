package com.codelab.proyectoab

import Jugador
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import com.codelab.proyectoab.navigation.AppNavigation
import com.codelab.proyectoab.ui.theme.ProyectoABTheme
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.codelab.proyectoab.utils.NotificationChannelStore
import com.codelab.proyectoab.utils.PermissionHelper
import com.codelab.proyectoab.utils.PermissionStore

class MainActivity : ComponentActivity() {
    companion object {
        const val CLAVE_TEMA_OSCURO = "dark_theme"
        const val CLAVE_NOMBRE_USUARIO = "nombre_usuario"
        const val CLAVE_JUGADORES_EXPANDIDOS = "jugadores_expandidos"
        const val EXTRA_JUGADOR_ID = "jugador_id"
    }

    // Inicalizamos los stores
    var notificationChannelStore = NotificationChannelStore()
    var permissionStore = PermissionStore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // IMPORTANTE: Inicializar ANTES de setContent
        permissionStore.initialize(this)
        notificationChannelStore.register(this)

        // 2. Obtenemos SharedPreferences
        val prefs = getSharedPreferences("ajustes_usuario", Context.MODE_PRIVATE)

        // 3. Leemos la preferencia y aplicamos el tema
        val temaOscuro = prefs.getBoolean(CLAVE_TEMA_OSCURO, false)

        AppCompatDelegate.setDefaultNightMode(
            if (temaOscuro) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        // 4. Obtener valores del intent
        val jugadorIdDesdeNotificacion = intent.getIntExtra(EXTRA_JUGADOR_ID, -1)


        // 5. Resto del código existente
        setContent {
            ProyectoABTheme(darkTheme = temaOscuro) {
                AppNavigation(prefs = prefs, initialJugadorId = jugadorIdDesdeNotificacion)
            }
        }
    }
}

@Composable
fun MainScreen() {
    // contenido principal

}

