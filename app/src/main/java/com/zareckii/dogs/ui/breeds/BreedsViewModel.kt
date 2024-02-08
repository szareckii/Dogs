package com.zareckii.dogs.ui.breeds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.network.successOr
import com.zareckii.dogs.domain.usecase.breedusecase.FetchBreedsDbUseCase
import com.zareckii.dogs.domain.usecase.breedusecase.GetBreedsDbUseCase
import com.zareckii.dogs.domain.usecase.breedusecase.SearchBreedAscUseCase
import com.zareckii.dogs.domain.usecase.breedusecase.SearchBreedDescUseCase
import com.zareckii.dogs.ui.breeds.models.BreedsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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
) : ViewModel() {

    private val _viewState = MutableStateFlow(BreedsViewState())
    val viewState: StateFlow<BreedsViewState> = _viewState

    init {
        _viewState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            launch {
                val breedsFlow = getBreedsDbUseCase(Unit).successOr(emptyFlow())
                breedsFlow
                    .distinctUntilChanged()
//                    .debounce(3000)
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
                fetchBreedsDbUseCase(Unit)
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

    fun onClickAsdDesc() {
        viewModelScope.launch {
            val searchBreeds = _viewState.value.breeds.filter {
                _viewState.value.searchText.lowercase() in it.breedName.lowercase()
            }.reversed()

            Log.e("stas", "$searchBreeds")
            val sorted = _viewState.value.isSortedAsc ?: false

            _viewState.update { it.copy(searchBreeds = searchBreeds, isSortedAsc = !sorted) }

        }
    }
}
