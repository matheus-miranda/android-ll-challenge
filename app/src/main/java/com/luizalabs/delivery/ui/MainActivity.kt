package com.luizalabs.delivery.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.luizalabs.delivery.navigation.RootNavHost
import com.luizalabs.designsystem.theme.DeliveryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryAppTheme {
                RootNavHost()
            }
        }
    }
}
