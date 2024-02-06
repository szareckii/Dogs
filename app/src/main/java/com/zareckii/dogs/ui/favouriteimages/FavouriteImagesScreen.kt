package com.zareckii.dogs.ui.favouriteimages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.components.AppBarDefault
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault
import com.zareckii.dogs.ui.favouriteimages.views.EmptyImagesDialog
import com.zareckii.dogs.ui.favouriteimages.views.FavoriteImageItem

@Composable
fun FavoriteImagesScreen(
    breed: String,
    favouriteImagesViewModel: FavouriteImagesViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {

    val viewState = favouriteImagesViewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(breed) {
        favouriteImagesViewModel.init(breed)
    }

    with(viewState.value) {
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            topBar = {
                AppBarDefault(
                    title = stringResource(R.string.favourites_photo),
                    onClickBack = onClickBack
                )
            }
        ) { padding ->
            if (isLoading) {
                CircularProgressIndicatorDefault()
            } else if (images.isEmpty()) {
                EmptyImagesDialog(
                    dialogTitle = stringResource(R.string.ups),
                    dialogText = stringResource(R.string.lis_image_empty),
                    onDismissRequest = onClickBack,
                    onConfirmation = onClickBack
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(MaterialTheme.colors.primary),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = images, key = { it }) { image ->
                        FavoriteImageItem(
                            image = image,
                            onClickRemoveFavorite = {
                                favouriteImagesViewModel.removeFavoriteImage(image)
                            }
                        )
                    }
                }
            }
        }
    }
}
