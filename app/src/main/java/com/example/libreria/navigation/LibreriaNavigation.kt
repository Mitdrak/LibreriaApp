package com.example.libreria.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.libreria.ui.screens.home.HomeScreemViewModel
import com.example.libreria.ui.screens.home.HomeScreen
import com.example.libreria.ui.screens.libro.BookDetailScreen

@Composable
fun LibreriaNavigation(contentPaddingValues: PaddingValues, navController: NavHostController) {
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
            BookDetailScreen(
                navController = navController,
                bookId = bookId ?: "2",
                contentPaddingValues = contentPaddingValues,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}