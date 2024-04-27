package com.luizalabs.registration.presentation.detail

import com.luizalabs.registration.presentation.detail.helper.UiText

data class DetailUiState(
    val isLoading: Boolean = false,
    val uiError: UiText? = null,
    val isNewForm: Boolean = true,
    val formSaved: Boolean = false,
    val cityListRetrieved: Boolean = false,
    val id: Int = 0,
    val parcelQuantity: Int = 0,
    val deliveryDeadline: Long = System.currentTimeMillis(),
    val clientName: String = "",
    val clientCpf: String = "",
    val zipCode: String = "",
    val state: String = "",
    val city: String = "",
    val neighborhood: String = "",
    val street: String = "",
    val number: String = "",
    val complement: String? = null,
)
