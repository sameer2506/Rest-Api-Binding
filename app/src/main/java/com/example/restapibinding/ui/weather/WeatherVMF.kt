package com.example.restapibinding.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restapibinding.data.repositories.WeatherRepository

@Suppress("UNCHECKED_CAST")
class WeatherVMF(
    private val repository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherVM(repository) as T
    }
}