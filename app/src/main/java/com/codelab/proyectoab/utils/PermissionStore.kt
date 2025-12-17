package com.codelab.proyectoab.utils

import android.Manifest
import androidx.activity.ComponentActivity

// Esta clase almacenara todos los permisos de la aplicacion y lo gestionara
// permission helper
class PermissionStore {
    // Permisos privados
    private val permissions = listOf(
        PermissionHelper(permission = Manifest.permission.POST_NOTIFICATIONS)
    )

    // Permisos
    val notificationPermission get() = permissions[0]

    // Función para inicializar todos los permisos de la lista
    fun initialize(activity: ComponentActivity) {
        permissions.forEach { it.initialize(activity) }
    }
}