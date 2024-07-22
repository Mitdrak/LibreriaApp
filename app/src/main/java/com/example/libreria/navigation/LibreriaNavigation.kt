package com.example.libreria.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libreria.ui.screens.home.HomeScreemViewModel
import com.example.libreria.ui.screens.home.HomeScreen

@Composable
fun LibreriaNavigation(contentPaddingValues: PaddingValues) {
    val navController = rememberNavController()
    val libreriaViewModel: HomeScreemViewModel = viewModel(factory = HomeScreemViewModel.Factory)

    NavHost(navController = navController, startDestination = LibreriaScreens.HomeScreen.name) {
        composable(LibreriaScreens.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                libreriaUiState = libreriaViewModel.libreriaUiState,
                contentPadding = contentPaddingValues,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable("BookScreen/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
        }

    }
}