package com.luizalabs.registration.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luizalabs.designsystem.AppDatePickerDialog
import com.luizalabs.designsystem.AppDropdownMenu
import com.luizalabs.designsystem.AppTextField
import com.luizalabs.designsystem.AppTopBar
import com.luizalabs.designsystem.LoadingIndicator
import com.luizalabs.registration.R
import com.luizalabs.registration.domain.model.City
import com.luizalabs.registration.presentation.common.toDateString
import com.luizalabs.registration.presentation.detail.helper.stateList
import com.luizalabs.ui.debouncedClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreen(
    onNavigateUp: () -> Unit,
    uiState: DetailUiState,
    cityList: List<City>,
    onEvent: (DetailUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            AppTopBar(
                title = if (uiState.isNewForm) {
                    stringResource(R.string.new_delivery)
                } else stringResource(
                    R.string.edit_delivery
                ),
                shouldNavigateUp = true,
                onNavigateUp = { onNavigateUp.invoke() },
                shouldShowDelete = uiState.isNewForm.not(),
                onDeleteClick = {
                    onEvent(DetailUiEvent.DeleteForm)
                    onNavigateUp.invoke()
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            ShowSnackBarErrorMessages(uiState, coroutineScope, snackBarHostState, onEvent)

            MainContent(
                innerPadding,
                uiState,
                onEvent,
                cityList,
                onNavigateUp,
            )
        },
    )
}

@Composable
private fun ShowSnackBarErrorMessages(
    uiState: DetailUiState,
    coroutineScope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    onEvent: (DetailUiEvent) -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.uiError) {
        coroutineScope.launch {
            uiState.uiError?.let {
                snackBarHostState.showSnackbar(message = it.asString(context))
            }
            onEvent.invoke(DetailUiEvent.SnackBarShown)
        }
    }
}

@Composable
private fun MainContent(
    innerPadding: PaddingValues,
    uiState: DetailUiState,
    onEvent: (DetailUiEvent) -> Unit,
    cityList: List<City>,
    onNavigateUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (uiState.isLoading) {
            LoadingIndicator()
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AppTextField(
                value = uiState.clientName,
                onValueChange = { onEvent.invoke(DetailUiEvent.ClientNameEdit(it)) },
                leadingIcon = Icons.Filled.Person,
                label = stringResource(R.string.name)
            )
            AppTextField(
                value = uiState.clientCpf,
                onValueChange = { onEvent.invoke(DetailUiEvent.ClientCpfEdit(it)) },
                leadingIcon = Icons.Filled.Person,
                label = stringResource(R.string.id)
            )
            AppTextField(
                value = if (uiState.parcelQuantity == 0) "" else uiState.parcelQuantity.toString(),
                onValueChange = { number ->
                    try {
                        number.toInt()
                        onEvent.invoke(DetailUiEvent.ParcelQuantityEdit(number.toInt()))
                    } catch (_: Exception) {
                        onEvent.invoke(DetailUiEvent.ParcelQuantityEdit(0))
                    }
                },
                label = stringResource(R.string.number_of_parcels),
                leadingIcon = Icons.Filled.Numbers,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                var showDatePicker by rememberSaveable {
                    mutableStateOf(false)
                }
                Text(text = stringResource(R.string.delivery_date))
                OutlinedButton(onClick = { showDatePicker = true }) {
                    Text(
                        text = uiState.deliveryDeadline.toDateString()
                    )
                }
                if (showDatePicker) {
                    AppDatePickerDialog(
                        onDateSelected = {
                            onEvent.invoke(DetailUiEvent.DeliveryDeadlineEdit(it))
                        }, onDismiss = {
                            showDatePicker = false
                        })
                }
            }

            AppTextField(
                value = uiState.street,
                onValueChange = { onEvent.invoke(DetailUiEvent.StreetEdit(it)) },
                leadingIcon = Icons.Filled.House,
                label = stringResource(R.string.street_name)
            )
            AppTextField(
                value = uiState.number,
                onValueChange = { onEvent.invoke(DetailUiEvent.NumberEdit(it)) },
                leadingIcon = Icons.Filled.House,
                label = stringResource(R.string.house_number)
            )
            AppTextField(
                value = uiState.neighborhood,
                onValueChange = { onEvent.invoke(DetailUiEvent.NeighborhoodEdit(it)) },
                leadingIcon = Icons.Filled.House,
                label = stringResource(R.string.district)
            )
            AppTextField(
                value = uiState.zipCode,
                onValueChange = { onEvent.invoke(DetailUiEvent.ZipcodeEdit(it)) },
                leadingIcon = Icons.Filled.House,
                label = stringResource(R.string.zipcode)
            )
            AppTextField(
                value = uiState.complement.orEmpty(),
                onValueChange = { onEvent.invoke(DetailUiEvent.ComplementEdit(it)) },
                leadingIcon = Icons.Filled.Warehouse,
                label = stringResource(R.string.complement)
            )

            AppDropdownMenu(
                value = uiState.state,
                label = stringResource(R.string.state),
                itemList = stateList,
                onSelectedItem = { onEvent.invoke(DetailUiEvent.StateEdit(it)) })

            AppDropdownMenu(
                value = uiState.city,
                enabled = uiState.cityListRetrieved,
                itemList = cityList.map { it.name },
                label = stringResource(R.string.city),
                onSelectedItem = { onEvent.invoke(DetailUiEvent.CityEdit(it)) })
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            onClick = debouncedClick { onEvent.invoke(DetailUiEvent.SubmitForm) }
        ) {
            Text(text = stringResource(R.string.save))
        }
        LaunchedEffect(key1 = uiState.formSaved) {
            if (uiState.formSaved) onNavigateUp.invoke()
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen(onNavigateUp = {}, uiState = DetailUiState(), cityList = listOf(), onEvent = {})
}
