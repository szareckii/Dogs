package com.zareckii.dogs.ui.breeddetail.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.breeds.models.ImageUi

@Composable
fun LandscapeType(
    currentImage: ImageUi,
    breed: String,
    padding: PaddingValues,
    onClickLike: () -> Unit,
    onClickNext: () -> Unit
) {
    Row(modifier = Modifier.padding(padding)) {
        AsyncImage(
            model = currentImage.imageUrl,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.6F)
                .clip(shape = RoundedCornerShape(2.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            error = painterResource(R.drawable.ic_dog)
        )
        DetailButtons(
            currentImage = currentImage,
            breed = breed,
            onClickLike = onClickLike,
            onClickNext = onClickNext
        )
    }
}