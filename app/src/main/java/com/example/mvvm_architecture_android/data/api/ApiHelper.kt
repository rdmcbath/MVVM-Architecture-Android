package com.example.mvvm_architecture_android.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}