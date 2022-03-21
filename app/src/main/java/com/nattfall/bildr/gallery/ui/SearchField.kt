package com.nattfall.bildr.gallery.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nattfall.bildr.R
import com.nattfall.bildr.ui.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(modifier: Modifier = Modifier, onSearchDone: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .height(searchFieldHeightDp)
                .fillMaxWidth(0.8f)
                .padding(8.dp),
            value = text,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colors.onSurface
                )
            },
            onValueChange = { newValue ->
                if (newValue != text) {
                    text = newValue
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onSearchDone.invoke(text)
                }
            )
        )


        Box(
            modifier = modifier.height(searchFieldHeightDp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(8.dp),
                onClick = { onSearchDone.invoke(text) }) {
                Text(text = stringResource(id = R.string.perform_search))
            }
        }
    }
}

@Preview
@Composable
fun SearchFieldPreview() {
    AppTheme {
        SearchField(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            onSearchDone = { }
        )
    }
}

private val searchFieldHeightDp = 80.dp