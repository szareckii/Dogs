package com.zareckii.dogs.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault
import com.zareckii.dogs.ui.favorites.views.BreedFavoriteItem

@Composable
fun FavoritesScreen(
    innerPadding: PaddingValues,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    onClickBreed: (String) -> Unit
) {

    val viewState = favoritesViewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        favoritesViewModel.init()
    }

    with(viewState.value) {
        if (isLoading)
            CircularProgressIndicatorDefault()
        else if (breeds.isEmpty())
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(R.string.no_favorites),
                    fontSize = 24.sp
                )
            }
        else
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.LightGray.copy(0.2F)),
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
