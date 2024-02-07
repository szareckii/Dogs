package com.zareckii.dogs.ui.favouriteimages.views

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zareckii.dogs.R

@Composable
fun EmptyImagesDialog(
    dialogTitle: String,
    dialogText: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {

    AlertDialog(
        title = {
            Text(text = dialogTitle, style = MaterialTheme.typography.h5)
        },
        text = {
            Text(text = dialogText, style = MaterialTheme.typography.body1)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmation
            ) { Text(stringResource(R.string.ok), style = MaterialTheme.typography.body1) }
        }
    )
}
