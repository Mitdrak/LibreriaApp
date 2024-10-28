package com.example.libreria.ui.screens.libro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.libreria.ui.screens.home.HomeScreemViewModel

@Composable
fun BookDetailScreen(
    bookId: String,
    navController: NavHostController,
    libreriaViewModel: HomeScreemViewModel,
    contentPaddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    println(
        "YA ESTOY EN SCREEN NUEVA ${bookId} ${navController.currentDestination}) and " + "${navController.previousBackStackEntry}"
    )
    libreriaViewModel.getBook(bookId)
    Box(
        modifier = modifier.padding(
            top = contentPaddingValues.calculateTopPadding()
        )

    ) {
        Text(
            text = "Book Detail Screen $bookId.",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}