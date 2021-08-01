package com.example.restapibinding.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapibinding.data.network.Resource
import com.example.restapibinding.data.network.responses.weather.WeatherDetails
import com.example.restapibinding.data.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherVM(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherDetails : MutableLiveData<Resource<WeatherDetails>> = MutableLiveData()
    val weatherDetails : LiveData<Resource<WeatherDetails>>
        get() = _weatherDetails

    fun getWeatherDetails(city: String, key: String) = viewModelScope.launch {
        _weatherDetails.value = Resource.Loading
        _weatherDetails.value = repository.getWeatherDetails(city, key)
    }

    suspend fun getUserRoomData() =
        withContext(Dispatchers.IO) {
            repository.getUser()
        }
}