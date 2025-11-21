package com.codelab.proyectoab.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.proyectoab.R

@Composable
@Preview(showBackground = true)
fun Logo(modifier: Modifier = Modifier) {
    // Imagen que se va ha usar como logo
    val image = painterResource(R.drawable.albacete_balompie);
    val description = stringResource(R.string.titulo_principal);

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = image,
            contentDescription = description,
            modifier = modifier
                .height(120.dp)
                .width(120.dp)
        )
    }
}