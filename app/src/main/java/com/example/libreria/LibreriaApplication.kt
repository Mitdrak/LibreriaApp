package com.example.libreria

import android.app.Application
import com.example.libreria.data.AppContainer
import com.example.libreria.data.DefaultAppContainer

class LibreriaApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}