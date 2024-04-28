package com.luizalabs.registration.detail

import androidx.lifecycle.SavedStateHandle
import com.luizalabs.registration.domain.Either
import com.luizalabs.registration.domain.error.NetworkError
import com.luizalabs.registration.domain.model.City
import com.luizalabs.registration.domain.repository.CityRepository
import com.luizalabs.registration.domain.repository.DeliveryRepository
import com.luizalabs.registration.presentation.detail.DetailUiEvent
import com.luizalabs.registration.presentation.detail.DetailViewModel
import com.luizalabs.testing.MainDispatcherRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class DetailViewModelTest {

    class DetailViewModelEventTest {

        @get:Rule
        val mainDispatcherRule = MainDispatcherRule()

        private lateinit var viewModel: DetailViewModel
        private val deliveryRepository: DeliveryRepository = mockk(relaxed = true)
        private val cityRepository: CityRepository = mockk(relaxed = true)
        private val savedStateHandle: SavedStateHandle = SavedStateHandle(mapOf("form_id" to 1))

        @Before
        fun setup() {
            viewModel = DetailViewModel(deliveryRepository, cityRepository, savedStateHandle)
        }

        @Test
        fun uiState_whenEditParcelQuantity_thenUpdateParcelState() {
            viewModel.onEvent(DetailUiEvent.ParcelQuantityEdit(2))

            assertEquals(2, viewModel.uiState.value.parcelQuantity)
        }

        @Test
        fun uiState_whenEditDeliveryDeadline_thenUpdateDeliveryState() {
            viewModel.onEvent(DetailUiEvent.DeliveryDeadlineEdit(100L))

            assertEquals(100L, viewModel.uiState.value.deliveryDeadline)
        }

        @Test
        fun uiState_whenEditClientName_thenUpdateNameState() {
            viewModel.onEvent(DetailUiEvent.ClientNameEdit("Name"))

            assertEquals("Name", viewModel.uiState.value.clientName)
        }

        @Test
        fun uiState_whenEditClientCpf_thenUpdateCpfState() {
            viewModel.onEvent(DetailUiEvent.ClientCpfEdit("100.100.100-05"))

            assertEquals("100.100.100-05", viewModel.uiState.value.clientCpf)
        }

        @Test
        fun uiState_whenEditZipcode_thenUpdateZipcodeState() {
            viewModel.onEvent(DetailUiEvent.ZipcodeEdit("01111-282"))

            assertEquals("01111-282", viewModel.uiState.value.zipCode)
        }

        @Test
        fun uiState_whenEditState_thenUpdateClientState() {
            viewModel.onEvent(DetailUiEvent.StateEdit("AC"))

            assertEquals("AC", viewModel.uiState.value.state)
        }

        @Test
        fun uiState_whenFetchCityListSuccessful_thenRetrieveListAndSetEmptyCity() {
            val cityReturn = listOf(City("City1"))
            coEvery { cityRepository.getCitiesByState(any()) } returns Either.Success(cityReturn)

            viewModel.onEvent(DetailUiEvent.StateEdit("AC"))

            coVerify(exactly = 1) { cityRepository.getCitiesByState(any()) }
            assertEquals(cityReturn, viewModel.cityList.value)
            assertEquals("", viewModel.uiState.value.city)
            assertTrue(viewModel.uiState.value.cityListRetrieved)
        }

        @Test
        fun uiState_whenFailureFetchingCityList_thenDoNotSetCityListRetrieved() {
            coEvery { cityRepository.getCitiesByState(any()) } returns Either.Failure(NetworkError.NO_INTERNET)

            viewModel.onEvent(DetailUiEvent.StateEdit("AC"))

            assertEquals(emptyList<City>(), viewModel.cityList.value)
            assertFalse(viewModel.uiState.value.cityListRetrieved)
            assertNotNull(viewModel.uiState.value.uiError)
        }

        @Test
        fun uiState_whenEditCityName_thenUpdateCityState() = runTest {
            viewModel.onEvent(DetailUiEvent.CityEdit("City Edit"))

            assertEquals("City Edit", viewModel.uiState.value.city)
        }

        @Test
        fun uiState_whenEditNeighborhood_thenUpdateNeighborhoodState() {
            viewModel.onEvent(DetailUiEvent.NeighborhoodEdit("Neighborhood"))

            assertEquals("Neighborhood", viewModel.uiState.value.neighborhood)
        }

        @Test
        fun uiState_whenEditStreet_thenUpdateStreetState() {
            viewModel.onEvent(DetailUiEvent.StreetEdit("Street"))

            assertEquals("Street", viewModel.uiState.value.street)
        }

        @Test
        fun uiState_whenEditNumber_thenUpdateNumberState() {
            viewModel.onEvent(DetailUiEvent.NumberEdit("Number"))

            assertEquals("Number", viewModel.uiState.value.number)
        }

        @Test
        fun uiState_whenEditComplement_thenUpdateComplementState() {
            viewModel.onEvent(DetailUiEvent.ComplementEdit("Complement"))

            assertEquals("Complement", viewModel.uiState.value.complement)
        }

        @Test
        fun uiState_whenSnackBarShown_thenUpdateErrorMessageState() {
            viewModel.onEvent(DetailUiEvent.SnackBarShown)

            assertEquals(null, viewModel.uiState.value.uiError)
        }

        @Test
        fun uiState_whenSubmitFormInvalid_thenShowErrorAndDoNotSubmit() {
            viewModel.onEvent(DetailUiEvent.SubmitForm)

            assertNotNull(viewModel.uiState.value.uiError)
            coVerify(exactly = 0) { deliveryRepository.saveForm(any()) }
        }

        @Test
        fun uiState_whenSubmitFormIsValid_thenSaveAndSetFormSavedValueTrue() {
            viewModel.onEvent(DetailUiEvent.ClientNameEdit("ClientNameEdit"))
            viewModel.onEvent(DetailUiEvent.ClientCpfEdit("ClientCpfEdit"))
            viewModel.onEvent(DetailUiEvent.CityEdit("CityEdit"))
            viewModel.onEvent(DetailUiEvent.StateEdit("StateEdit"))
            viewModel.onEvent(DetailUiEvent.StreetEdit("StreetEdit"))
            viewModel.onEvent(DetailUiEvent.NumberEdit("NumberEdit"))
            viewModel.onEvent(DetailUiEvent.NeighborhoodEdit("NeighborhoodEdit"))
            viewModel.onEvent(DetailUiEvent.ZipcodeEdit("ZipcodeEdit"))
            viewModel.onEvent(DetailUiEvent.ParcelQuantityEdit(2))
            viewModel.onEvent(DetailUiEvent.SubmitForm)

            coVerify(exactly = 1) { deliveryRepository.saveForm(any()) }
            assertTrue(viewModel.uiState.value.formSaved)
        }

        @Test
        fun deliveryRepository_whenDeleteForm_thenCallFormDelete() {
            viewModel.onEvent(DetailUiEvent.DeleteForm)

            coVerify(exactly = 1) { deliveryRepository.deleteFormById(any()) }
        }

        companion object {
            @JvmStatic
            @AfterClass
            fun tearDown() {
                clearAllMocks()
            }
        }
    }

    class DetailViewModelInitializationTest {

        @get:Rule
        val mainDispatcherRule = MainDispatcherRule()

        private lateinit var viewModel: DetailViewModel
        private val deliveryRepository: DeliveryRepository = mockk(relaxed = true)
        private val cityRepository: CityRepository = mockk(relaxed = true)

        @Test
        fun deliveryRepository_whenSavedStateHandleIdArgLessThanZero_thenCreateNewForm() {
            viewModel = DetailViewModel(deliveryRepository, cityRepository, setSavedStateHandle(-1))

            coVerify(exactly = 0) { deliveryRepository.getFormById(any()) }
        }

        @Test
        fun deliveryRepository_whenSavedStateHandleIdArgGreaterThanZero_thenRecoverFormFromRepo() {
            viewModel = DetailViewModel(deliveryRepository, cityRepository, setSavedStateHandle())

            coVerify { deliveryRepository.getFormById(any()) }
        }

        companion object {
            @JvmStatic
            @AfterClass
            fun tearDown() {
                clearAllMocks()
            }
        }

        private fun setSavedStateHandle(id: Int = 1) = SavedStateHandle(mapOf("form_id" to id))
    }
}
