package com.example.libreria.data

import LoggingInterceptor
import com.example.libreria.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val libreriaRepository: LibreriaRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"
    val json = Json { ignoreUnknownKeys = true }
    val contentType = "application/json".toMediaType()

    private val loggingInterceptor = LoggingInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
        .baseUrl(BASE_URL).build()
    private val retroFitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
    override val libreriaRepository: LibreriaRepository by lazy {
        DefaultLibreriaRepository(retroFitService)
    }
}