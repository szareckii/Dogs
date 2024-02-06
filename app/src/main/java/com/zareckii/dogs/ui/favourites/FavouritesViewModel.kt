package com.zareckii.dogs.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.successOr
import com.zareckii.dogs.domain.GetFavoriteBreedsUseCase
import com.zareckii.dogs.ui.favourites.models.FavouritesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavoriteBreedsUseCase: GetFavoriteBreedsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(FavouritesViewState())
    val viewState: StateFlow<FavouritesViewState> = _viewState

    fun init() {
        viewModelScope.launch {
            val favoriteBreedsFlow = getFavoriteBreedsUseCase(Unit).successOr(emptyFlow())
            favoriteBreedsFlow
                .distinctUntilChanged()
                .collect { favoriteBreeds ->
                    _viewState.update {
                        it.copy(
                            breeds = favoriteBreeds,
                            isLoading = false
                        )
                    }
                }
        }
    }
}
