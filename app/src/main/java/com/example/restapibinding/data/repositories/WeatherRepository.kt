package com.example.restapibinding.data.repositories

import com.example.restapibinding.data.db.AppDatabase
import com.example.restapibinding.data.network.SafeApiRequest
import com.example.restapibinding.data.network.WeatherApi

class WeatherRepository(
    private val api : WeatherApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun getWeatherDetails(city: String, key: String) = safeApiRequest {
        api.getWeatherDetails(city, key)
    }

    fun getUser() = db.getUserDao().getUserDetails()
}