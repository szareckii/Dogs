package com.zareckii.dogs.ui.favoriteimages.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zareckii.dogs.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteImageItem(
    image: String,
    modifier: Modifier = Modifier,
    onClickNotFavorite: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClickNotFavorite
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                error = painterResource(R.drawable.ic_dog)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onClickNotFavorite) {
                Text(
                    text = "Remove favorite",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}
