package com.example.restapibinding.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restapibinding.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class RegistrationVMF(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegistrationVM(repository) as T
    }
}