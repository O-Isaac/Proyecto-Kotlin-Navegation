package com.codelab.proyectoab.utils

import android.Manifest
import android. content.Context
import android.content.Intent
import android.content. pm.PackageManager
import android.net.Uri
import android.os.Build
import android. provider.Settings
import android.util.Log
import androidx.activity. ComponentActivity
import androidx.activity. result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHelper(
    private val permission: String,
    private var onPermissionGranted: () -> Unit = {},
    private var onPermissionDenied: (canRetry: Boolean) -> Unit = {}
) {
    private lateinit var launcher: ActivityResultLauncher<String>
    private lateinit var activity: ComponentActivity

    // Setters

    fun setOnPermissionDenied(callback: (canRetry: Boolean) -> Unit) {
        onPermissionDenied = callback
    }

    fun setOnPermissionGranted(callback: () -> Unit) {
        onPermissionGranted = callback
    }

    /**
     * Inicializa el launcher desde una Activity
     */
    fun initialize(activity: ComponentActivity) {
        this.activity = activity
        launcher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            when {
                isGranted -> {
                    // Permiso concedido
                    Log.d("PermissionHelper", "Permiso concedido")
                    onPermissionGranted()
                }
                ActivityCompat.shouldShowRequestPermissionRationale(activity, permission) -> {
                    // El usuario denegó, pero puede volver a pedírselo
                    Log.d("PermissionHelper", "Usuario denegó - puede volver a pedir")
                    onPermissionDenied(true) // canRetry = true
                }
                else -> {
                    // El usuario marcó "No volver a preguntar"
                    Log.d("PermissionHelper", "Permiso bloqueado permanentemente")
                    onPermissionDenied(false) // canRetry = false
                }
            }
        }
    }

    /**
     * Pide un permiso
     */
    fun request() {
        if (:: launcher.isInitialized) {
            Log.d("PermissionHelper", "Solicitando permiso:  $permission")
            launcher.launch(permission)
        } else {
            throw IllegalStateException("Debes llamar a initialize() antes de request()")
        }
    }

    /**
     * Comprueba si tiene un permiso
     */
    fun hasPermission(context: Context): Boolean {
        if (permission == Manifest.permission.POST_NOTIFICATIONS && Build.VERSION.SDK_INT <= Build.VERSION_CODES.TIRAMISU) {
            // En Android <13, no se necesita permiso explícito
            return true;
        }

        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Abre la configuración de la aplicación
     */
    fun openAppSettings() {
        if (!::activity.isInitialized) {
            throw IllegalStateException("Debes llamar a initialize() antes de openAppSettings()")
        }
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", activity.packageName, null)
        }
        activity. startActivity(intent)
    }
}