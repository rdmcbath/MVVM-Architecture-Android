package com.example.mvvm_architecture_android.ui.view.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mvvm_architecture_android.data.repository.MainRepository
import com.example.mvvm_architecture_android.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}