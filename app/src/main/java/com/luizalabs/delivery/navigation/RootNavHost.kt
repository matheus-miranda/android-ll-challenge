package com.luizalabs.delivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.luizalabs.registration.navgraph.REGISTRATION_GRAPH_ROUTE
import com.luizalabs.registration.navgraph.featureRegistrationGraph
import com.luizalabs.ui.screenFadeIn
import com.luizalabs.ui.screenFadeOut
import com.luizalabs.ui.screenSlideIn
import com.luizalabs.ui.screenSlideOut

@Composable
internal fun RootNavHost(
    rootNavController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = rootNavController,
        startDestination = REGISTRATION_GRAPH_ROUTE,
        enterTransition = { screenSlideIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenSlideOut() },
    ) {
        featureRegistrationGraph(
            navController = rootNavController,
        )
    }
}
