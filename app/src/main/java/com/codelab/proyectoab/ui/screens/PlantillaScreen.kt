package com.codelab.proyectoab.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelab.proyectoab.ui.components.ExtensibleCard
import com.codelab.proyectoab.ui.models.DatoModel

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PlantillaScreen(modifier: Modifier = Modifier, viewModel: DatoModel = viewModel(), onClick: (String) -> Unit = {}) {
    val jugadores = viewModel.jugadores;

    Surface {
        LazyColumn {
            items(items = jugadores, key = { it.id }) {
                ExtensibleCard(modifier, it) { perfilUrl ->
                    onClick(perfilUrl)
                }
            }
        }
    }

}