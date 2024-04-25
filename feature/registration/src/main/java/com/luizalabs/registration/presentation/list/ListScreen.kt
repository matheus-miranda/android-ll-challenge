package com.luizalabs.registration.presentation.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.luizalabs.designsystem.AppTopBar
import com.luizalabs.designsystem.LoadingIndicator
import com.luizalabs.registration.R
import com.luizalabs.registration.domain.model.DeliveryAddress
import com.luizalabs.registration.domain.model.DeliveryForm
import com.luizalabs.registration.presentation.list.components.CenterScreenText
import com.luizalabs.registration.presentation.list.components.DeliveryList
import com.luizalabs.registration.presentation.list.components.FabButton
import com.luizalabs.ui.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListScreen(
    uiState: ListUiState,
    modifier: Modifier = Modifier,
    onAddNewDeliveryClick: () -> Unit,
    onViewCardDetailsClick: (Int) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.delivery_list),
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding -> MainContent(innerPadding, uiState, onViewCardDetailsClick) },
        floatingActionButton = { FabButton(onAddNewDeliveryClick) },
    )
}

@Composable
private fun MainContent(
    innerPadding: PaddingValues,
    uiState: ListUiState,
    onViewCardDetailsClick: (Int) -> Unit,
) {
    when (uiState) {
        ListUiState.Loading -> LoadingIndicator()
        ListUiState.Empty -> CenterScreenText(stringResource(R.string.click_the_icon_below))
        ListUiState.Error -> CenterScreenText(stringResource(R.string.error_fetching_from_the_database))
        is ListUiState.Success -> DeliveryList(innerPadding, uiState, onViewCardDetailsClick)
    }
}

@DevicePreviews
@Composable
private fun ListScreenPreview() {
    val previewData = DeliveryForm(
        id = 5225,
        parcelQuantity = 4,
        deliveryDeadline = 1713723470000,
        clientName = "Terry Cash",
        clientCpf = "222.222.222-00",
        address = DeliveryAddress(
            zipCode = "13000-000",
            state = "Arkansas",
            city = "Little Rock",
            neighborhood = "County",
            street = "Brentwood Rd.",
            number = "1103",
            complement = "School"
        )
    )
    val list = List(6) { previewData.copy(id = it) }

    ListScreen(
        uiState = ListUiState.Success(list),
        onAddNewDeliveryClick = {},
        onViewCardDetailsClick = {}
    )
}
