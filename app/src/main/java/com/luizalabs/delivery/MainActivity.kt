package com.luizalabs.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.luizalabs.delivery.navigation.RootNavHost
import com.luizalabs.delivery.ui.theme.DeliveryAppTheme

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
