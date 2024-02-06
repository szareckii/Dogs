package com.zareckii.dogs.ui.breeddetail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.breeds.models.ImageUi

@Composable
fun DetailButtons(
    currentImage: ImageUi,
    breed: String,
    onClickLike: () -> Unit,
    onClickNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = breed.capitalize(Locale.current),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Button(
            onClick = onClickLike,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
        ) {
            Icon(
                imageVector = if (currentImage.isFavorite) Icons.Filled.Star else Icons.Outlined.StarOutline,
                contentDescription = stringResource(
                    if (currentImage.isFavorite) R.string.remove else R.string.add
                ),
                modifier = Modifier.padding(end = 4.dp),
                tint = MaterialTheme.colors.error
            )
            Text(
                text = stringResource(
                    if (currentImage.isFavorite)
                        R.string.remove_favourite
                    else
                        R.string.add_favourite
                )
            )
        }
        Button(
            onClick = onClickNext,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}
