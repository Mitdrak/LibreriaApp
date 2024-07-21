package com.example.libreria.ui.screens.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.libreria.LibreriaApplication
import com.example.libreria.data.LibreriaRepository
import com.example.libreria.model.BooksResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import java.io.IOException

sealed interface LibreriaUiState {
    data class Success(val libros: BooksResponse) : LibreriaUiState
    object Error : LibreriaUiState
    object Loading : LibreriaUiState
}

class HomeScreemViewModel(private val libreriaRepository: LibreriaRepository) : ViewModel() {
    var libreriaUiState: LibreriaUiState by mutableStateOf(LibreriaUiState.Loading)
        private set

    init {
        getLibros("Dune")
    }

    fun getLibros(query: String) {
        viewModelScope.launch {
            libreriaUiState = LibreriaUiState.Loading
            libreriaUiState = try {
                LibreriaUiState.Success(libreriaRepository.getLibrosReponse(query))
            } catch (e: IOException) {
                println("Error $e")
                LibreriaUiState.Error
            } catch (e: Exception) {
                println("Error $e")
                LibreriaUiState.Error
            }
        }
    }


    companion object {
        // Factory to create instances of AmphibiansViewModel
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            // Initializer block to create the ViewModel instance
            initializer {
                // Get the AmphibiansApplication instance
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LibreriaApplication)
                // Get the AmphibiansRepository instance from the application container
                val libreriaRepository = application.container.libreriaRepository
                // Create and return the AmphibiansViewModel instance
                HomeScreemViewModel(libreriaRepository = libreriaRepository)
            }
        }
    }
}