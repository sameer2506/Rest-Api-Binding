package com.example.restapibinding.data.repositories

import com.example.restapibinding.data.db.AppDatabase
import com.example.restapibinding.data.db.entities.User
import com.example.restapibinding.data.network.PincodeApi
import com.example.restapibinding.data.network.SafeApiRequest

class WeatherRepository(
    private val api : PincodeApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    fun getUser() = db.getUserDao().getUserDetails()
}