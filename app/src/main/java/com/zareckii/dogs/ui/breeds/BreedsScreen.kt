package com.zareckii.dogs.ui.breeds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.zareckii.dogs.ui.breeds.views.BreedItem
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault

@Composable
fun BreedsScreen(
    breedsViewModel: BreedsViewModel = hiltViewModel(),
    onClick: (String) -> Unit
) {

    val viewState = breedsViewModel.viewState.collectAsStateWithLifecycle()

    with(viewState.value) {
        if (isLoading)
            CircularProgressIndicatorDefault()
        else
            LazyColumn(
                modifier = Modifier.padding(),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = breeds, key = { it.breedName }) { breed ->
                    BreedItem(
                        breed = breed,
                        onClick = { onClick(breed.breedName) }
                    )
                }
            }
    }

}
