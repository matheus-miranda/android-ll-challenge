package com.luizalabs.delivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.luizalabs.registration.navgraph.REGISTRATION_GRAPH_ROUTE
import com.luizalabs.registration.navgraph.featureRegistrationGraph

@Composable
internal fun RootNavHost() {
    val rootNavController = rememberNavController()

    NavHost(navController = rootNavController, startDestination = REGISTRATION_GRAPH_ROUTE) {
        featureRegistrationGraph(
            navController = rootNavController,
        )
    }
}
