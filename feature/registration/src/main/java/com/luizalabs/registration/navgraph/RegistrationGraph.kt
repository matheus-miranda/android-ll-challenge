package com.luizalabs.registration.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.luizalabs.registration.presentation.list.navigation.LIST_ROUTE
import com.luizalabs.registration.presentation.list.navigation.listScreen

const val REGISTRATION_GRAPH_ROUTE = "registration_graph"

fun NavGraphBuilder.featureRegistrationGraph(navController: NavHostController) {
    navigation(
        route = REGISTRATION_GRAPH_ROUTE,
        startDestination = LIST_ROUTE
    ) {
        listScreen()
    }
}
