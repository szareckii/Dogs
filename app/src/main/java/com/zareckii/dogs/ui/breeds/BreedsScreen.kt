package com.zareckii.dogs.ui.breeds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.ui.breeds.views.BreedItem
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault

@Composable
fun BreedsScreen(
    innerPadding: PaddingValues,
    breedsViewModel: BreedsViewModel = hiltViewModel(),
    onClickBread: (String) -> Unit
) {

    val viewState = breedsViewModel.viewState.collectAsStateWithLifecycle()

    with(viewState.value) {
        if (isLoading)
            CircularProgressIndicatorDefault()
        else
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colors.primary),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = breeds, key = { it.breedName }) { breed ->
                    BreedItem(
                        breed = breed,
                        onClick = { onClickBread(breed.breedName) }
                    )
                }
            }
    }

}
