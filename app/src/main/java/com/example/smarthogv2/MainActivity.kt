package com.example.smarthogv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.smarthogv2.ui.navigation.SmartHogAppNavigation
import com.example.smarthogv2.ui.theme.SmartHogV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartHogV2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SmartHogAppNavigation()
                }
            }
        }
    }
}
