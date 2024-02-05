package com.zareckii.dogs.ui.favoriteimages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.components.AppBarDefault
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault
import com.zareckii.dogs.ui.favoriteimages.views.FavoriteImageItem

@Composable
fun FavoriteImagesScreen(
    breed: String,
    favoriteImagesViewModel: FavoriteImagesViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {

    val viewState = favoriteImagesViewModel.viewState.collectAsStateWithLifecycle()
    LaunchedEffect(breed) {
        favoriteImagesViewModel.init(breed)
    }

    with(viewState.value) {
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            topBar = {
                AppBarDefault(
                    title = stringResource(R.string.favorite_photo),
                    onClickBack = onClickBack
                )
            }
        ) { padding ->
            if (isLoading)
                CircularProgressIndicatorDefault()
            else if (images.isEmpty()) {
                Text(
                    text = "No favorites image",
                    modifier = Modifier.padding(16.dp)
                )
            } else
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(Color.LightGray.copy(0.9F)),
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = images, key = { it }) { image ->
                        FavoriteImageItem(
                            image = image,
                            onClickNotFavorite = { favoriteImagesViewModel.removeFavoriteImage(image) }
                        )
                    }
                }

        }
    }
}
