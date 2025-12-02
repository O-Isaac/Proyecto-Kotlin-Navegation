package com.codelab.proyectoab.ui.components

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelab.proyectoab.MainActivity
import androidx.core.content.edit

fun cambiarNombre(nombreTemporal: String, isSaved: MutableState<Boolean>, prefs: SharedPreferences) {
    prefs.edit {
        putString(MainActivity.CLAVE_NOMBRE_USUARIO, nombreTemporal.trim())
        isSaved.value = true
    }
}

@Composable
fun GuardarNombreComponent(modifier: Modifier = Modifier, prefs: SharedPreferences) {
    val nombreGuardado = prefs.getString(MainActivity.CLAVE_NOMBRE_USUARIO, "") ?: ""
    var nombreTemporal by remember { mutableStateOf(nombreGuardado) }
    val isSaved = remember { mutableStateOf(nombreGuardado.isNotEmpty()) }

    // Pedir nombre si no hay nombre guardado
    if (!isSaved.value) {
        OutlinedTextField(
            value = nombreTemporal,
            onValueChange = { nombreTemporal = it },
            label = { Text("Tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { cambiarNombre(nombreTemporal, isSaved, prefs) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Guardar nombre") }
    } else {
        Text(
            text = "¡Hola, $nombreTemporal!",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}