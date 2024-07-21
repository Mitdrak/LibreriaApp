package com.example.libreria.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libreria.ui.screens.home.HomeScreemViewModel
import com.example.libreria.ui.screens.home.HomeScreen
import com.example.libreria.ui.theme.Orange40


//How to put color to the scaffold topappbar?


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibreriaApp() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Libreria", style = MaterialTheme.typography.headlineMedium
                )
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Orange40
            )
        )
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val libreriaViewModel: HomeScreemViewModel =
                viewModel(factory = HomeScreemViewModel.Factory)
            HomeScreen(
                libreriaUiState = libreriaViewModel.libreriaUiState,
                contentPadding = it,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Preview
@Composable
fun LibreriaAppReview() {
    LibreriaApp()
}