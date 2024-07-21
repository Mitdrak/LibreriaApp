package com.example.libreria.navigation

enum class LibreriaScreens {
    HomeScreen, ShelfScreen, BookScreen;

    companion object {
        fun fromRoute(route: String): LibreriaScreens = when (route.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            ShelfScreen.name -> ShelfScreen
            BookScreen.name -> BookScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }
    }
}