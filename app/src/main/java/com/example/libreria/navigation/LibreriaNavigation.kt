package com.example.libreria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LibreriaNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LibreriaScreens.HomeScreen.name) {
        composable(LibreriaScreens.HomeScreen.name) {

        }

    }
}