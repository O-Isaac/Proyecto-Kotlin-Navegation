package com.codelab.proyectoab.ui.screens

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelab.proyectoab.MainActivity
import com.codelab.proyectoab.ui.components.ExtensibleCard
import com.codelab.proyectoab.ui.models.PlantillaModel

@Composable
fun PlantillaScreen(
    prefs: SharedPreferences,
    model: PlantillaModel = PlantillaModel.plantillaViewModel(prefs),
    onClick: (String) -> Unit = {}
) {
    Surface {
        LazyColumn {
            items(items = model.jugadoresUI, key = { it.jugador.id }) {
                ExtensibleCard(
                    jugador = it.jugador,
                    isExpanded = it.isExpanded.value,
                    onExpandedChange = { model.switchExpandedCard(it.jugador.id) },
                    onClickPerfil = { urlPerfil -> onClick(urlPerfil) }
                )
            }
        }
    }

}