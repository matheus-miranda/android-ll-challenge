package com.luizalabs.registration.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luizalabs.registration.domain.repository.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: DeliveryRepository,
) : ViewModel() {

    val uiState: StateFlow<ListUiState> = channelFlow {
        try {
            repository.getForms().collectLatest {
                if (it.isEmpty()) {
                    trySend(ListUiState.Empty)
                } else {
                    trySend(ListUiState.Success(it))
                }
            }
        } catch (e: Exception) {
            trySend(ListUiState.Error)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ListUiState.Loading
    )
}
