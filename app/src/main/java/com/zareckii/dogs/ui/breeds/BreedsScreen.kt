package com.zareckii.dogs.ui.breeds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.ui.breeds.views.BreedItem
import com.zareckii.dogs.ui.breeds.views.BreedsNotFound
import com.zareckii.dogs.ui.breeds.views.ExpandableSearchView
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault

@Composable
fun BreedsScreen(
    innerPadding: PaddingValues,
    breedsViewModel: BreedsViewModel = hiltViewModel(),
    onClickBread: (String) -> Unit
) {

    val viewState = breedsViewModel.viewState.collectAsStateWithLifecycle()

    with(viewState.value) {
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                ) {
                    ExpandableSearchView(
                        modifier = Modifier.weight(6F),
                        searchDisplay = searchText,
                        showSearch = showSearch,
                        onSearchDisplayChanged = { breedsViewModel.onSearchTextChange(it) },
                        onSearchDisplayClosed = { breedsViewModel.onSearchTextChange("") },
                        onExpandedChanged = { breedsViewModel.onExpandedChanged(it) },
                    )

                    IconButton(
                        modifier = Modifier.weight(1F),
                        onClick = breedsViewModel::onClickAsdDesc
                    ) {
                        Icon(
                            Icons.Default.ArrowDownward, contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        ) { padding ->
            if (isLoading)
                CircularProgressIndicatorDefault()
            else if (
                (searchText.isEmpty() && breeds.isEmpty())
                || (searchText.isNotEmpty() && searchBreeds.isEmpty())
            )
                BreedsNotFound()
            else LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(padding)
                    .background(MaterialTheme.colors.primary),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = if (searchText.isEmpty()) breeds else searchBreeds,
                    key = { it.breedName }) { breed ->
                    BreedItem(
                        breed = breed,
                        onClick = { onClickBread(breed.breedName) }
                    )
                }
            }
        }
    }
}
