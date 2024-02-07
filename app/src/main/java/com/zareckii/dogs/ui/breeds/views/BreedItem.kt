package com.zareckii.dogs.ui.breeds.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.zareckii.dogs.ui.breeds.models.BreedUi

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BreedItem(
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
        onClick = onClick
    ) {
        Text(
            text = breed.breedName.capitalize(Locale.current),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body1
        )
    }

}
