package com.codelab.proyectoab

sealed class Router(val route: String) {
    data object Incio : Router("inicio")
    data object Plantilla : Router("plantilla")
}