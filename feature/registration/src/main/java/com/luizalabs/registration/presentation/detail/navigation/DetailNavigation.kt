package com.luizalabs.registration.presentation.detail.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luizalabs.registration.presentation.detail.DetailScreen
import com.luizalabs.registration.presentation.detail.DetailViewModel

private const val FORM_ID = "form_id"
internal const val DETAIL_ROUTE = "detail?formId={$FORM_ID}"

internal class DetailScreenArgs(val formId: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        formId = checkNotNull(savedStateHandle[FORM_ID])
    )
}

internal fun NavController.navigateToDetailScreen(id: Int? = null) {
    val createRoute = "detail?formId=$id"
    navigate(createRoute)
}

internal fun NavGraphBuilder.detailScreen(
    onNavigateUp: () -> Unit,
) {
    composable(
        DETAIL_ROUTE,
        arguments = listOf(
            navArgument(FORM_ID) {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        val viewModel: DetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val cityList by viewModel.cityList.collectAsStateWithLifecycle()

        DetailScreen(
            uiState = uiState,
            cityList = cityList,
            onEvent = viewModel::onEvent,
            onNavigateUp = onNavigateUp,
        )
    }
}
