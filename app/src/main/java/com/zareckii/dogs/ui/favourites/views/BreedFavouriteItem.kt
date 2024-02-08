package com.zareckii.dogs.ui.favourites.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.breeds.models.BreedUi

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BreedFavoriteItem(
    breed: BreedUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = breed.imageUrl,
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                error = painterResource(R.drawable.ic_dog)
            )
            Text(
                text = breed.breedName.capitalize(Locale.current),
                modifier = Modifier.padding(16.dp)
            )

        }
    }

}
