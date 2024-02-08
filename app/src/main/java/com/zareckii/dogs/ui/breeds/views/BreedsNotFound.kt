package com.zareckii.dogs.ui.breeds.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zareckii.dogs.R

@Composable
fun BreedsNotFound() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.breeds_not_found),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary
        )
    }
}
