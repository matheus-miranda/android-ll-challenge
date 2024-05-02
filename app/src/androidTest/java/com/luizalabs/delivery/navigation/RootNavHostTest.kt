package com.luizalabs.delivery.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.luizalabs.delivery.HiltComponentActivity
import com.luizalabs.registration.presentation.list.navigation.LIST_ROUTE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RootNavHostTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            RootNavHost(navController)
        }
    }

    @Test
    fun navHost_verifyStartDestinationGraph() {
        val currentDestination = navController.currentBackStackEntry?.destination?.route
        assertEquals(LIST_ROUTE, currentDestination)
    }
}
