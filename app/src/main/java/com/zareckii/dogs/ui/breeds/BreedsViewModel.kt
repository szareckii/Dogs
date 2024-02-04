package com.zareckii.dogs.ui.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.successOr
import com.zareckii.dogs.domain.FetchBreedsDbUseCase
import com.zareckii.dogs.domain.GetBreedsDbUseCase
import com.zareckii.dogs.ui.breeds.models.BreedsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {

    private val _viewState = MutableStateFlow(BreedsViewState())
    val viewState: StateFlow<BreedsViewState> = _viewState

    init {
        _viewState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            launch {
                val breedsFlow = getBreedsDbUseCase(Unit).successOr(emptyFlow())
                breedsFlow.distinctUntilChanged().collect { breeds ->
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

}
