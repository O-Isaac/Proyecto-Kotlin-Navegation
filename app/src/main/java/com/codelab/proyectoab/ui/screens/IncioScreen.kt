package com.codelab.proyectoab.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.proyectoab.ui.components.Logo
import com.codelab.proyectoab.R

@Composable
fun InicioScreen(modifier: Modifier = Modifier, onClick: (Int) -> Unit) {
    val titulo = stringResource(R.string.titulo_inicio);
    val descripcion = stringResource(R.string.descripcion_inicio)
    val anio = stringResource(R.string.anio_fundacion)
    val socios = stringResource(R.string.numero_socios)
    val estadio = stringResource(R.string.nombre_estadio)
    val btnPlantilla = stringResource(R.string.boton_ver_plantilla)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()

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
            Button(onClick = { onClick(1)}) {
                Text(
                    text = btnPlantilla,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}