package com.zareckii.dogs.ui.breeddetail

import android.content.res.Configuration
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.breeddetail.models.BreedDetailAction
import com.zareckii.dogs.ui.breeddetail.views.LandscapeType
import com.zareckii.dogs.ui.breeddetail.views.PortraitType
import com.zareckii.dogs.ui.components.AppBarDefault
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault
import com.zareckii.dogs.ui.components.SnackbarDefault

@Composable
fun BreedDetailScreen(
    breed: String,
    breedDetailViewModel: BreedDetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {

    val viewState = breedDetailViewModel.viewState.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val orientationLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(breed) {
        breedDetailViewModel.init(breed)
    }

    with(viewState.value) {
        LifecycleStartEffect {
            onStopOrDispose {
                breedDetailViewModel.actionInvoked()
            }
        }

        Scaffold(
            scaffoldState = rememberScaffoldState(),
            snackbarHost = {
                SnackbarHost(hostState = it) { data ->
                    SnackbarDefault(
                        modifier = Modifier.offset(y = (-70).dp),
                        snackbarData = data,
                    )
                }
            },
            topBar = {
                AppBarDefault(title = stringResource(R.string.detail), onClickBack = onClickBack)
            }
        ) { padding ->
            if (isLoading)
                CircularProgressIndicatorDefault()
            else if (currentImage != null)
                if (orientationLandscape)
                    LandscapeType(
                        currentImage = currentImage,
                        breed = breedName,
                        padding = padding,
                        onClickLike = breedDetailViewModel::onClickLike,
                        onClickNext = breedDetailViewModel::onClickNext
                    )
                else
                    PortraitType(
                        currentImage = currentImage,
                        breed = breedName,
                        padding = padding,
                        onClickLike = breedDetailViewModel::onClickLike,
                        onClickNext = breedDetailViewModel::onClickNext
                    )
        }

        LaunchedEffect(breedDetailAction) {
            when (breedDetailAction) {
                is BreedDetailAction.SnackbarShow -> {
                    scaffoldState.snackbarHostState.showSnackbar(snackbarMessage)
                }

                else -> {}
            }
        }

    }

}
