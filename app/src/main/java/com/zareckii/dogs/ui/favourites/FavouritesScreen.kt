package com.zareckii.dogs.ui.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault
import com.zareckii.dogs.ui.favourites.views.BreedFavoriteItem

@Composable
fun FavoritesScreen(
    innerPadding: PaddingValues,
    favouritesViewModel: FavouritesViewModel = hiltViewModel(),
    onClickBreed: (String) -> Unit
) {

    val viewState = favouritesViewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        favouritesViewModel.init()
    }

    with(viewState.value) {
        if (isLoading)
            CircularProgressIndicatorDefault()
        else if (breeds.isEmpty())
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_favourites),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
            }
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
                    BreedFavoriteItem(
                        breed = breed,
                        onClick = { onClickBreed(breed.breedName) }
                    )
                }
            }
    }
}
