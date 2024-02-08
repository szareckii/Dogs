package com.zareckii.dogs.ui.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.R
import com.zareckii.dogs.data.network.Result
import com.zareckii.dogs.data.network.successOr
import com.zareckii.dogs.domain.usecase.breedusecase.FetchBreedsDbUseCase
import com.zareckii.dogs.domain.usecase.breedusecase.GetBreedsDbUseCase
import com.zareckii.dogs.domain.usecase.breedusecase.SearchBreedAscUseCase
import com.zareckii.dogs.domain.usecase.breedusecase.SearchBreedDescUseCase
import com.zareckii.dogs.ui.breeds.models.BreedsAction
import com.zareckii.dogs.ui.breeds.models.BreedsViewState
import com.zareckii.dogs.utils.ManageResources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsDbUseCase: GetBreedsDbUseCase,
    private val fetchBreedsDbUseCase: FetchBreedsDbUseCase,
    private val searchBreedAscUseCase: SearchBreedAscUseCase,
    private val searchBreedDescUseCase: SearchBreedDescUseCase,
    private val resources: ManageResources
) : ViewModel() {

    private val _viewState = MutableStateFlow(BreedsViewState())
    val viewState: StateFlow<BreedsViewState> = _viewState

    init {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            launch {
                val breedsFlow = getBreedsDbUseCase(Unit).successOr(emptyFlow())
                breedsFlow
                    .cancellable()
                    .collect { breeds ->
                        _viewState.update {
                            it.copy(
                                breeds = breeds,
                                isLoading = false
                            )
                        }
                    }
            }
            launch {
                val result = fetchBreedsDbUseCase(Unit)
                if (result is Result.Success)
                    _viewState.update { it.copy(breedsAction = BreedsAction.None) }
                else {
                    _viewState.update {
                        it.copy(
                            breedsAction = BreedsAction.SnackbarShow,
                            snackbarMessage = resources.string(R.string.connection_error)
                        )
                    }
                }
            }
        }
    }

    fun onSearchTextChange(searchText: String) {
        val searchBreeds = _viewState.value.breeds.filter {
            searchText.lowercase() in it.breedName.lowercase()
        }
        _viewState.update {
            it.copy(
                searchText = searchText,
                searchBreeds = searchBreeds
            )
        }
    }

    fun onExpandedChanged(showSearch: Boolean) =
        _viewState.update {
            it.copy(
                searchText = "",
                showSearch = showSearch,
                searchBreeds = emptyList()
            )
        }

    fun onSearchDisplayClosed() =
        _viewState.update {
            it.copy(
                searchText = "",
                showSearch = false,
                isSortedAsc = null
            )
        }

    fun onClickSorted() {
        viewModelScope.launch {
            val isSortedAsc = _viewState.value.isSortedAsc ?: true
            val search = _viewState.value.searchText.lowercase()
            val orderedBreed = if (isSortedAsc)
                searchBreedDescUseCase(search).successOr(emptyList())
            else
                searchBreedAscUseCase(search).successOr(emptyList())

            _viewState.update {
                it.copy(
                    searchBreeds = orderedBreed,
                    isSortedAsc = !isSortedAsc
                )
            }
        }
    }

    fun actionInvoked() = _viewState.update { it.copy(breedsAction = BreedsAction.None) }
}
