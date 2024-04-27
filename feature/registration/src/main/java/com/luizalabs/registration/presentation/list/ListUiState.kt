package com.luizalabs.registration.presentation.list

import com.luizalabs.registration.domain.model.DeliveryForm

sealed interface ListUiState {
    data object Loading : ListUiState
    data object Empty : ListUiState
    data object Error : ListUiState
    data class Success(val list: List<DeliveryForm>) : ListUiState
}
