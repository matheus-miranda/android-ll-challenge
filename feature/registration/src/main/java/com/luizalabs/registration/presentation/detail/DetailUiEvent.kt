package com.luizalabs.registration.presentation.detail

sealed interface DetailUiEvent {
    data class ParcelQuantityEdit(val quantity: Int) : DetailUiEvent
    data class DeliveryDeadlineEdit(val deadline: Long) : DetailUiEvent
    data class ClientNameEdit(val name: String) : DetailUiEvent
    data class ClientCpfEdit(val cpf: String) : DetailUiEvent
    data class ZipcodeEdit(val zipcode: String) : DetailUiEvent
    data class StateEdit(val state: String) : DetailUiEvent
    data class CityEdit(val city: String) : DetailUiEvent
    data class NeighborhoodEdit(val neighborhood: String) : DetailUiEvent
    data class StreetEdit(val street: String) : DetailUiEvent
    data class NumberEdit(val number: String) : DetailUiEvent
    data class ComplementEdit(val complement: String) : DetailUiEvent
    data object SnackBarShown : DetailUiEvent
    data object SubmitForm : DetailUiEvent
    data object DeleteForm : DetailUiEvent
}
