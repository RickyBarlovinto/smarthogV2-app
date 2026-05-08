package com.example.smarthogv2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarthogv2.ui.screens.DashboardScreen
import com.example.smarthogv2.ui.screens.LoginScreen
import com.example.smarthogv2.ui.screens.WelcomeScreen

@Composable
fun SmartHogAppNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(onNavigateToLogin = {
                navController.navigate("login")
            })
        }
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("dashboard") {
                    popUpTo("welcome") { inclusive = true }
                }
            })
        }
        composable("dashboard") {
            DashboardScreen()
        }
    }
}
