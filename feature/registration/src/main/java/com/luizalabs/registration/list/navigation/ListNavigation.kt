package com.luizalabs.registration.list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.luizalabs.registration.list.ListScreen

const val LIST_ROUTE = "list"

internal fun NavGraphBuilder.listScreen() {
    composable(LIST_ROUTE) {
        ListScreen()
    }
}
