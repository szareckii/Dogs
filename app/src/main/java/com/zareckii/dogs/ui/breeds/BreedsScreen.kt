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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.ui.breeds.views.BreedItem
import com.zareckii.dogs.ui.breeds.views.BreedsNotFound
import com.zareckii.dogs.ui.breeds.views.ExpandableSearchView
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault
import kotlinx.coroutines.launch

@Composable
fun BreedsScreen(
    innerPadding: PaddingValues,
    breedsViewModel: BreedsViewModel = hiltViewModel(),
    onClickBread: (String) -> Unit
) {

    val viewState = breedsViewModel.viewState.collectAsStateWithLifecycle()

    val lazyState = rememberLazyListState()
    val scope = rememberCoroutineScope()

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
                        onClick = {
                            scope.launch {
                                breedsViewModel.onClickAsdDesc()
                                lazyState.scrollToItem(0)
                            }
                        }
                    ) {
                        Icon(
                            if (isSortedAsc == null || isSortedAsc) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        ) { padding ->
            if (isLoading)
                CircularProgressIndicatorDefault()
            else if ((searchText.isEmpty() && breeds.isEmpty()) || (searchText.isNotEmpty() && searchBreeds.isEmpty()))
                BreedsNotFound()
            else LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(padding)
                    .background(MaterialTheme.colors.primary),
                state = lazyState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = if (searchText.isEmpty() && isSortedAsc == null) breeds else searchBreeds,
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
