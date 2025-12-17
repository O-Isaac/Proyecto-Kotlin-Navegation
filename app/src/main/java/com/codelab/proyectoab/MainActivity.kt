package com.codelab.proyectoab

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import com.codelab.proyectoab.navigation.AppNavigation
import com.codelab.proyectoab.ui.theme.ProyectoABTheme

class MainActivity : ComponentActivity() {
    companion object {
        const val CLAVE_TEMA_OSCURO = "dark_theme"
        const val CLAVE_NOMBRE_USUARIO = "nombre_usuario"
        const val CLAVE_JUGADORES_EXPANDIDOS = "jugadores_expandidos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. Obtenemos SharedPreferences
        val prefs = getSharedPreferences("ajustes_usuario", Context.MODE_PRIVATE)

        // 3. Leemos la preferencia y aplicamos el tema
        val temaOscuro = prefs.getBoolean(CLAVE_TEMA_OSCURO, false)

        AppCompatDelegate.setDefaultNightMode(
            if (temaOscuro) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        // 4. Resto del código existente
        setContent {
            ProyectoABTheme(darkTheme = temaOscuro) {
                AppNavigation(prefs = prefs)
            }
        }
    }
}

@Composable
fun MainScreen() {
    // contenido principal

}

