package com.zareckii.dogs.ui.breeddetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.zareckii.dogs.R
import com.zareckii.dogs.ui.components.AppBarDefault
import com.zareckii.dogs.ui.components.CircularProgressIndicatorDefault

@Composable
fun BreedDetailScreen(
    breed: String,
    breedDetailViewModel: BreedDetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {

    val viewState = breedDetailViewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(breed) {
        breedDetailViewModel.init(breed)
    }

    with(viewState.value) {
        Scaffold(
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
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = breedDetailViewModel::onClickLike,
                            modifier = Modifier.weight(0.5F),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (currentImage?.isFavorite == true)
                                    Color.Red
                                else
                                    Color.Blue
                            )
                        ) {
                            Text(
                                text = stringResource(
                                    if (currentImage?.isFavorite == true)
                                        R.string.remove_favorite
                                    else
                                        R.string.add_favorite
                                )
                            )
                        }
                        Button(
                            onClick = breedDetailViewModel::onClickNext,
                            modifier = Modifier.weight(0.5F)
                        ) {
                            Text(text = stringResource(R.string.next))
                        }
                    }
                }
        }
    }
}
