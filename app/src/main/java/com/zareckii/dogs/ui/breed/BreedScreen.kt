package com.zareckii.dogs.ui.breed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.components.AppBarDefault
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault

@Composable
fun BreedScreen(
    breed: String,
    modifier: Modifier = Modifier,
    breedViewModel: BreedViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {

    val viewState = breedViewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(breed) {
        breedViewModel.init(breed)
    }

    with(viewState.value) {
        Scaffold(
            modifier = modifier,
            scaffoldState = rememberScaffoldState(),
            topBar = {
                AppBarDefault(title = stringResource(R.string.detail), onClickBack = onClickBack)
            }
        ) { padding ->
            if (isLoading)
                CircularProgressIndicatorDefault()
            else
                Column(modifier = Modifier.padding(padding)) {
                    AsyncImage(
                        model = currentImage?.imageUrl ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5F)
                            .clip(shape = RoundedCornerShape(2.dp)),
                        contentScale = ContentScale.Fit,
                        contentDescription = null,
                        error = painterResource(R.drawable.ic_dog)
                    )
                    Text(
                        text = breed.capitalize(Locale.current),
                        modifier = Modifier.padding(16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = breedViewModel::onClickLike,
                            modifier = Modifier.weight(0.5F)
                        ) {
                            Text(text = "Like")
                        }
                        Button(
                            onClick = breedViewModel::onClickNext,
                            modifier = Modifier.weight(0.5F)
                        ) {
                            Text(text = "Next")
                        }
                    }
                }
        }
    }
}
