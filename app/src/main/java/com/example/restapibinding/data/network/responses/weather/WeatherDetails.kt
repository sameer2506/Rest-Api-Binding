package com.example.restapibinding.data.network.responses.weather

data class WeatherDetails(
    val coord: Coord,
    val main: Main,
    val name: String
)