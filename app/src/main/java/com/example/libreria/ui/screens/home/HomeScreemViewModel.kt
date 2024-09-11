package com.example.libreria.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    /*data class Success(val libros: BooksResponse) : LibreriaUiState*/
    data class Success(val libros: BooksResponse) : LibreriaUiState
    object Error : LibreriaUiState
    object Loading : LibreriaUiState
}

class HomeScreemViewModel(private val libreriaRepository: LibreriaRepository) : ViewModel() {

    private val _libreriaUiState = MutableStateFlow<LibreriaUiState>(LibreriaUiState.Loading)
    val libreriaUiState: StateFlow<LibreriaUiState> = _libreriaUiState.asStateFlow()
//    private val _libreriaUiState = MutableStateFlow<LibreriaUiState>(LibreriaUiState.Loading)
//
//    var libreriaUiState: LibreriaUiState by mutableStateOf(LibreriaUiState.Loading)
//        private set
    val _libros = MutableLiveData<BooksResponse>()
    val libros: LiveData<BooksResponse> = _libros

    init {
        getLibros("Brandon Sanderson")
        getBook("fQBiAwAAQBAJ")
    }

    fun getLibros(query: String) {
        println("Se imprime lo que se ve a buscar $query")
        viewModelScope.launch {
            _libreriaUiState.value = LibreriaUiState.Loading
            _libreriaUiState.value = try {
                val result = libreriaRepository.getLibrosReponse(query)
                _libros.value = result
                println("Se imprime el resultado $result")
                /*LibreriaUiState.Success(libreriaRepository.getLibrosReponse(query))*/
                LibreriaUiState.Success(libros = result)
            } catch (e: IOException) {
                println("Error IOException $e")
                LibreriaUiState.Error
            } catch (e: Exception) {
                println("Error Exception $e")
                LibreriaUiState.Error
            }
        }
    }
    fun getBook(id: String) {
        viewModelScope.launch {
            try {
                val result = libreriaRepository.getBook(id)
                println("Se imprime el resultado del libro $id : $result")
            } catch (e: IOException) {
                println("Error IOException $e")
            } catch (e: Exception) {
                println("Error Exception $e")
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