package com.codelab.proyectoab.ui.models

import RepositorioJugadores
import androidx.lifecycle.ViewModel

class DatoModel : ViewModel() {
    private val _repository = RepositorioJugadores
    private val _jugadores = _repository.getJugadores().toMutableList()

    // Getters y setter
    val jugadores get() = _jugadores.toList();
}
