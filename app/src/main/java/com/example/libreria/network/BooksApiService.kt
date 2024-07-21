package com.example.libreria.network

import com.example.libreria.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getAllbooks(@Query("q") query: String): BooksResponse
}