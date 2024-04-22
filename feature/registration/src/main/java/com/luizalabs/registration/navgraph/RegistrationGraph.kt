package com.luizalabs.registration.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.luizalabs.registration.presentation.detail.navigation.detailScreen
import com.luizalabs.registration.presentation.detail.navigation.navigateToDetailScreen
import com.luizalabs.registration.presentation.list.navigation.LIST_ROUTE
import com.luizalabs.registration.presentation.list.navigation.listScreen

const val REGISTRATION_GRAPH_ROUTE = "registration_graph"

fun NavGraphBuilder.featureRegistrationGraph(navController: NavHostController) {
    navigation(
        route = REGISTRATION_GRAPH_ROUTE,
        startDestination = LIST_ROUTE
    ) {
        listScreen(
            onAddNewDeliveryClick = navController::navigateToDetailScreen,
            onViewCardDetailsClick = navController::navigateToDetailScreen,
        )
        detailScreen(
            onNavigateUp = navController::navigateUp
        )
    }
}
