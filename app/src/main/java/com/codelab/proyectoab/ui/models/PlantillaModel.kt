package com.codelab.proyectoab.ui.models

import Jugador
import JugadorUI
import RepositorioJugadores
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codelab.proyectoab.MainActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class PlantillaModel(private val prefs: SharedPreferences) : ViewModel() {
    private val _jugadoresUI = mutableListOf<JugadorUI>()
    val jugadoresUI: List<JugadorUI> = _jugadoresUI;

    init {
        // Estados iniciales
        val jugadores = RepositorioJugadores.getJugadores();
        val expandidos = prefs
            .getStringSet(MainActivity.CLAVE_JUGADORES_EXPANDIDOS, emptySet())
            .orEmpty()

        // Funcion auxiliar para mapear los jugadores
        fun mapToJugadorUI(jugador: Jugador): JugadorUI {
            val id = jugador.id.toString();
            val isExpanded = expandidos.contains(id)

            return JugadorUI(
                jugador = jugador,
                isExpanded = mutableStateOf(isExpanded)
            )
        }

        // Añadir jugadores
        _jugadoresUI.addAll(
            jugadores.map(::mapToJugadorUI)
        )
    }

    companion object {
        // Factory para crear un modelo
        fun Factory(prefs: SharedPreferences): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PlantillaModel(prefs) as T
                }
            }
        }

        // Funcion viewModelPersonalizada para llamar
        @Composable
        fun plantillaViewModel(prefs: SharedPreferences) =
            viewModel<PlantillaModel>(factory = Factory(prefs))
    }


    fun switchExpandedCard(jugadorId: Int) {
        val jugadorUI = _jugadoresUI.find { it.jugador.id == jugadorId }

        jugadorUI?.let {
            it.isExpanded.value = !it.isExpanded.value
            guardarEstadoExpandido()
        }
    }

    fun guardarEstadoExpandido() {
        val expandidos = _jugadoresUI
            .filter { it.isExpanded.value }
            .map { it.jugador.id.toString() }
            .toSet()

        prefs.edit {
            putStringSet(MainActivity.CLAVE_JUGADORES_EXPANDIDOS, expandidos)
        }
    }
}
