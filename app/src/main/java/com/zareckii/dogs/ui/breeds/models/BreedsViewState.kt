package com.zareckii.dogs.ui.breeds.models

import androidx.compose.runtime.Immutable

sealed class BreedsAction {
    data object SnackbarShow : BreedsAction()
    data object None : BreedsAction()
}

@Immutable
data class BreedsViewState(
    val breedsAction: BreedsAction = BreedsAction.None,
    val isLoading: Boolean = false,
    val breeds: List<BreedUi> = emptyList(),
    val searchBreeds: List<BreedUi> = emptyList(),
    val searchText: String = "",
    val showSearch: Boolean = false,
    val isSortedAsc: Boolean? = null,
    val snackbarMessage: String = ""
)
