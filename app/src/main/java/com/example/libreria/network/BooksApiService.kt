package com.example.libreria.network

import com.example.libreria.model.BooksResponse
import com.example.libreria.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getAllbooks(@Query("q") query: String): BooksResponse

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Item

}