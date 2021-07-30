package com.example.restapibinding.data.repositories

import com.example.restapibinding.data.db.AppDatabase
import com.example.restapibinding.data.db.entities.User
import com.example.restapibinding.data.network.PincodeApi
import com.example.restapibinding.data.network.SafeApiRequest

class UserRepository(
    private val api : PincodeApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun getPincodeDetails(pincode: String) = safeApiRequest {
        api.getPincodeDetails(pincode)
    }

    suspend fun saveUser(user: User) = db.getUserDao().saveUser(user)
}