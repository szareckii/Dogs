package com.zareckii.dogs.ui.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zareckii.dogs.data.successOr
import com.zareckii.dogs.domain.GetBreedsUseCase
import com.zareckii.dogs.ui.breeds.models.BreedsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase

) : ViewModel() {

    private val _viewState = MutableStateFlow(BreedsViewState())
    val viewState: StateFlow<BreedsViewState> = _viewState

    init {
        _viewState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val breeds = getBreedsUseCase(Unit).successOr(null)
            _viewState.update {
                if (breeds == null)
                    it.copy(isLoading = false)
                else
                    it.copy(breeds = breeds, isLoading = false)
            }
        }
    }

}
