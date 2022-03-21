package com.nattfall.bildr.gallery.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nattfall.bildr.R
import com.nattfall.bildr.gallery.ui.FullScreenCard

@Composable
fun LoadingState() {
    FullScreenCard {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(id = R.string.gallery_loading_state_message),
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
            )
        }
    }
}