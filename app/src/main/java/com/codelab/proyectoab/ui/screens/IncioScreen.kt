package com.codelab.proyectoab.ui.screens

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codelab.proyectoab.MainActivity
import com.codelab.proyectoab.ui.components.Logo
import com.codelab.proyectoab.R
import androidx.core.content.edit
import androidx.navigation.NavController
import com.codelab.proyectoab.MainActivity.Companion.CLAVE_TEMA_OSCURO
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.codelab.proyectoab.ui.components.GuardarNombreComponent

@SuppressLint("UseKtx")
@Composable
fun InicioScreen(modifier: Modifier = Modifier, prefs: SharedPreferences, navController: NavController, onClick: () -> Unit) {
    val titulo = stringResource(R.string.titulo_inicio);
    val descripcion = stringResource(R.string.descripcion_inicio)
    val anio = stringResource(R.string.anio_fundacion)
    val socios = stringResource(R.string.numero_socios)
    val estadio = stringResource(R.string.nombre_estadio)
    val btnPlantilla = stringResource(R.string.boton_ver_plantilla)
    val temaOscuro = prefs.getBoolean(CLAVE_TEMA_OSCURO, false)

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo()

            GuardarNombreComponent(prefs = prefs)

            Spacer(Modifier.height(8.dp))

            Row {
                Text(
                    text = titulo,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Text(
                    text = descripcion,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Text(
                    text = anio,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Text(
                    text = socios,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Text(
                    text = estadio,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Button(onClick = { onClick() }) {
                    Text(
                        text = btnPlantilla,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Row {
                // Botón para tema claro
                Button(
                    onClick = {
                        prefs.edit { putBoolean(MainActivity.CLAVE_TEMA_OSCURO, false) }
                        (navController.context as MainActivity).recreate()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Modo Claro")
                }
            }

            Spacer(Modifier.height(16.dp))

            Row {
                Button(
                    onClick = {
                        prefs.edit().putBoolean(MainActivity.CLAVE_TEMA_OSCURO, true).apply()
                        (navController.context as MainActivity).recreate()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Modo Oscuro")
                }
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Text(
                    text = if (temaOscuro) "Modo: OSCURO" else "Modo: CLARO",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }
}