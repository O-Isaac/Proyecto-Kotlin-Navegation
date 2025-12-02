package com.codelab.proyectoab.navigation

import RepositorioJugadores
import android.content.SharedPreferences
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.codelab.proyectoab.ui.screens.DetalleJugadorScreen
import com.codelab.proyectoab.ui.screens.InicioScreen
import com.codelab.proyectoab.ui.screens.PlantillaScreen


@Composable
fun AppNavigation(prefs: SharedPreferences){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio"){
        composable("inicio") {
            InicioScreen(prefs = prefs, navController = navController){
                navController.navigate("plantilla")
            }
        }

        composable("plantilla") {
            PlantillaScreen() { urlPerfil ->
                navController.navigate(urlPerfil)
            }
        }

        composable(
            route = "detalle_jugador/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 1
            val jugador = RepositorioJugadores.getJugadorPorId(id);

            if (jugador != null) {
                DetalleJugadorScreen(jugador)
            } else {
                Text("Jugador no encontrado")
            }
        }
    }
}