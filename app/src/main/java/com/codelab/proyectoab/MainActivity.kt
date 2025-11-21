package com.codelab.proyectoab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.codelab.proyectoab.navigation.AppNavigation
import com.codelab.proyectoab.ui.theme.ProyectoABTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoABTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun MainScreen() {
    // contenido principal

}

