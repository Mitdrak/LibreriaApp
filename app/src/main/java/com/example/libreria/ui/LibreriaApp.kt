package com.example.libreria.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.libreria.navigation.LibreriaNavigation
import com.example.libreria.navigation.LibreriaScreens
import com.example.libreria.ui.screens.home.HomeScreemViewModel
import com.example.libreria.ui.screens.home.HomeScreen
import com.example.libreria.ui.theme.Orange40


//How to put color to the scaffold topappbar?


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibreriaApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack = navBackStackEntry != null && navController.previousBackStackEntry != null

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Libreria", style = MaterialTheme.typography.headlineMedium
            )
        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Orange40
        ), navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            } else null
        })
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            println("MI NAV CONTROLLER en libreriaApp es  ${navController.previousBackStackEntry}")
            LibreriaNavigation(it, navController)
        }
    }
}


@Preview
@Composable
fun LibreriaAppReview() {
    LibreriaApp()
}