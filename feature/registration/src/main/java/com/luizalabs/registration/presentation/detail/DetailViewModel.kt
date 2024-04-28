package com.luizalabs.registration.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luizalabs.registration.domain.Either
import com.luizalabs.registration.domain.error.NetworkError
import com.luizalabs.registration.domain.model.City
import com.luizalabs.registration.domain.model.DeliveryAddress
import com.luizalabs.registration.domain.model.DeliveryForm
import com.luizalabs.registration.domain.repository.CityRepository
import com.luizalabs.registration.domain.repository.DeliveryRepository
import com.luizalabs.registration.presentation.detail.helper.DetailScreenError
import com.luizalabs.registration.presentation.detail.helper.asUiText
import com.luizalabs.registration.presentation.detail.navigation.DetailScreenArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val formRepository: DeliveryRepository,
    private val cityRepository: CityRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _cityList = MutableStateFlow<List<City>>(emptyList())
    val cityList = _cityList.asStateFlow()

    private val args = DetailScreenArgs(savedStateHandle)

    init {
        updateFormValuesIfEditingEntry()
    }

    fun onEvent(uiEvent: DetailUiEvent) {
        when (uiEvent) {
            is DetailUiEvent.ParcelQuantityEdit -> _uiState.update { it.copy(parcelQuantity = uiEvent.quantity) }
            is DetailUiEvent.DeliveryDeadlineEdit -> _uiState.update { it.copy(deliveryDeadline = uiEvent.deadline) }
            is DetailUiEvent.ClientNameEdit -> _uiState.update { it.copy(clientName = uiEvent.name) }
            is DetailUiEvent.ClientCpfEdit -> _uiState.update { it.copy(clientCpf = uiEvent.cpf) }
            is DetailUiEvent.ZipcodeEdit -> _uiState.update { it.copy(zipCode = uiEvent.zipcode) }
            is DetailUiEvent.StateEdit -> stateEdit(uiEvent)
            is DetailUiEvent.CityEdit -> _uiState.update { it.copy(city = uiEvent.city) }
            is DetailUiEvent.NeighborhoodEdit -> _uiState.update { it.copy(neighborhood = uiEvent.neighborhood) }
            is DetailUiEvent.StreetEdit -> _uiState.update { it.copy(street = uiEvent.street) }
            is DetailUiEvent.NumberEdit -> _uiState.update { it.copy(number = uiEvent.number) }
            is DetailUiEvent.ComplementEdit -> _uiState.update { it.copy(complement = uiEvent.complement) }
            is DetailUiEvent.SnackBarShown -> _uiState.update { it.copy(uiError = null) }
            is DetailUiEvent.SubmitForm -> validateForm()
            is DetailUiEvent.DeleteForm -> deleteForm()
        }
    }

    private fun updateFormValuesIfEditingEntry() {
        if (args.formId >= 0) {
            _uiState.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                val editForm = formRepository.getFormById(args.formId)
                _uiState.update {
                    it.copy(
                        id = editForm.id,
                        parcelQuantity = editForm.parcelQuantity,
                        deliveryDeadline = editForm.deliveryDeadline,
                        clientName = editForm.clientName,
                        clientCpf = editForm.clientCpf,
                        zipCode = editForm.address.zipCode,
                        state = editForm.address.state,
                        city = editForm.address.city,
                        neighborhood = editForm.address.neighborhood,
                        street = editForm.address.street,
                        number = editForm.address.number,
                        complement = editForm.address.complement,
                        isLoading = false,
                        isNewForm = false,
                    )
                }
            }
        }
    }

    private fun stateEdit(uiEvent: DetailUiEvent.StateEdit) {
        _uiState.update { it.copy(state = uiEvent.state) }
        onStateSelected(_uiState.value.state)
    }

    private fun onStateSelected(state: String) {
        viewModelScope.launch {
            when (val cityResponse = cityRepository.getCitiesByState(state)) {
                is Either.Success -> {
                    _cityList.value = cityResponse.data
                    _uiState.update { it.copy(cityListRetrieved = true, city = "") }
                }

                is Either.Failure -> {
                    when (cityResponse.cause) {
                        NetworkError.NO_INTERNET -> _uiState.update { it.copy(uiError = NetworkError.NO_INTERNET.asUiText()) }
                        NetworkError.CONNECTION_ERROR -> _uiState.update { it.copy(uiError = NetworkError.CONNECTION_ERROR.asUiText()) }
                        NetworkError.REQUEST_TIMEOUT -> _uiState.update { it.copy(uiError = NetworkError.REQUEST_TIMEOUT.asUiText()) }
                        NetworkError.PAYLOAD_TOO_LARGE -> _uiState.update { it.copy(uiError = NetworkError.PAYLOAD_TOO_LARGE.asUiText()) }
                        NetworkError.PARSE_ERROR -> _uiState.update { it.copy(uiError = NetworkError.PARSE_ERROR.asUiText()) }
                        NetworkError.UNKNOWN_ERROR -> _uiState.update { it.copy(uiError = NetworkError.UNKNOWN_ERROR.asUiText()) }
                    }
                }
            }
        }
    }

    private fun validateForm() {
        if (_uiState.value.clientName.isBlank()
            || _uiState.value.clientCpf.isBlank()
            || _uiState.value.city.isBlank()
            || _uiState.value.state.isBlank()
            || _uiState.value.street.isBlank()
            || _uiState.value.number.isBlank()
            || _uiState.value.neighborhood.isBlank()
            || _uiState.value.zipCode.isBlank()
            || _uiState.value.parcelQuantity == 0
        ) {
            _uiState.update { it.copy(uiError = DetailScreenError.EMPTY_FIELDS.asUiText()) }
        } else {
            submitForm()
        }
    }

    private fun submitForm() {
        viewModelScope.launch {
            val form = DeliveryForm(
                id = _uiState.value.id,
                parcelQuantity = _uiState.value.parcelQuantity,
                deliveryDeadline = _uiState.value.deliveryDeadline,
                clientName = _uiState.value.clientName,
                clientCpf = _uiState.value.clientCpf,
                address = DeliveryAddress(
                    zipCode = _uiState.value.zipCode,
                    state = _uiState.value.state,
                    city = _uiState.value.city,
                    neighborhood = _uiState.value.neighborhood,
                    street = _uiState.value.street,
                    number = _uiState.value.number,
                    complement = _uiState.value.complement
                )
            )

            runCatching {
                formRepository.saveForm(form)
                _uiState.update { it.copy(isLoading = true) }
            }.onFailure {
                _uiState.update { it.copy(uiError = DetailScreenError.SUBMIT_ERROR.asUiText(), isLoading = false) }
            }.onSuccess {
                _uiState.update { it.copy(formSaved = true, isLoading = false) }
            }
        }
    }

    private fun deleteForm() {
        viewModelScope.launch {
            formRepository.deleteFormById(_uiState.value.id)
        }
    }
}
