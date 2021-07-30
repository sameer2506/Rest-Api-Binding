package com.example.restapibinding.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapibinding.data.db.entities.User
import com.example.restapibinding.data.network.Resource
import com.example.restapibinding.data.network.responses.pincode.PincodeDetails
import com.example.restapibinding.data.repositories.UserRepository
import kotlinx.coroutines.launch

class RegistrationVM(
    private val repository: UserRepository
) : ViewModel() {

    private val _pincodeDetails : MutableLiveData<Resource<PincodeDetails>> = MutableLiveData()
    val pincodeDetails : LiveData<Resource<PincodeDetails>>
        get() = _pincodeDetails

    fun getPinCodeDetails(pincode: String) = viewModelScope.launch {
        _pincodeDetails.value = Resource.Loading
        _pincodeDetails.value = repository.getPincodeDetails(pincode)
    }
    
    suspend fun saveUser(user: User) = repository.saveUser(user)
}