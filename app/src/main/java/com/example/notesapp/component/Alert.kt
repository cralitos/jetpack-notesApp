package com.example.notesapp.component

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Alert(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    val scrollState = rememberScrollState(0)
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(title) },
        text = {
            Text(
                text = message,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(scrollState)
            )
        },
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(confirmText)
            }
        }
    )
}
