package com.codelab.proyectoab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.codelab.proyectoab.ui.screens.InicioScreen
import com.codelab.proyectoab.ui.screens.PlantillaScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Inicio){
        composable<Inicio> {
            InicioScreen(){ idJugador ->
                navController.navigate(JugadoresRutas(idJugador))
            }
        }

        composable<JugadoresRutas>{ backStactkEntry ->
            val Jugador = backStactkEntry.toRoute<JugadoresRutas>()
            PlantillaScreen()
        }
    }
}