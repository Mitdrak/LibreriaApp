package com.example.libreria.data

import com.example.libreria.model.BooksResponse
import com.example.libreria.network.BooksApiService

interface LibreriaRepository {
    suspend fun getLibrosReponse(query: String): BooksResponse

}
class DefaultLibreriaRepository(
    private val libreriaApiService: BooksApiService
) : LibreriaRepository {
    override suspend fun getLibrosReponse(query: String): BooksResponse = libreriaApiService.getAllbooks(query)
}