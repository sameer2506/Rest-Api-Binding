package com.example.restapibinding.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapibinding.data.db.entities.User
import com.example.restapibinding.data.network.Resource
import com.example.restapibinding.data.network.responses.pincode.PincodeDetails
import com.example.restapibinding.data.repositories.UserRepository
import com.example.restapibinding.data.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherVM(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getUserRoomData() =
        withContext(Dispatchers.IO) {
            repository.getUser()
        }
}