package com.nattfall.bildr.gallery.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nattfall.bildr.R

@Composable
fun ErrorState(message: String) {
    FullScreenCard {
        Column {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.gallery_failed_state_message),
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = message,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
            )
        }
    }
}