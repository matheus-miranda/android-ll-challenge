package com.luizalabs.registration.presentation.list.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.luizalabs.registration.presentation.list.ListScreen
import com.luizalabs.registration.presentation.list.ListViewModel

const val LIST_ROUTE = "list"

internal fun NavGraphBuilder.listScreen(
    onAddNewDeliveryClick: () -> Unit,
    onViewCardDetailsClick: (Int) -> Unit,
) {
    composable(LIST_ROUTE) {
        val viewModel: ListViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        ListScreen(
            uiState = uiState,
            onAddNewDeliveryClick = onAddNewDeliveryClick,
            onViewCardDetailsClick = onViewCardDetailsClick,
        )
    }
}
