package com.example.libreria.data

import com.example.libreria.model.BooksResponse
import com.example.libreria.model.Item
import com.example.libreria.network.BooksApiService

interface LibreriaRepository {
    suspend fun getLibrosReponse(query: String): BooksResponse
    suspend fun getBook(id: String) : Item
}
class DefaultLibreriaRepository(
    private val libreriaApiService: BooksApiService
) : LibreriaRepository {
    override suspend fun getLibrosReponse(query: String): BooksResponse = libreriaApiService.getAllbooks(query)
    override suspend fun getBook(id: String) = libreriaApiService.getBook(id)
}