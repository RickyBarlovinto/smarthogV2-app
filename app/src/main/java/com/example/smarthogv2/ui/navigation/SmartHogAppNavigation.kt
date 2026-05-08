package com.example.smarthogv2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.smarthogv2.ui.screens.*

@Composable
fun SmartHogAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("dashboard") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("dashboard") {
            DashboardScreen()
        }
    }
}