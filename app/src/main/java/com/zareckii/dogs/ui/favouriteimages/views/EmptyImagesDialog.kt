package com.zareckii.dogs.ui.favouriteimages.views

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
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
            Text(text = dialogTitle, fontSize = 26.sp)
        },
        text = {
            Text(text = dialogText, fontSize = 20.sp)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmation
            ) { Text(stringResource(R.string.ok), fontSize = 26.sp) }
        }
    )
}
