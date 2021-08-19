package com.example.mvvm_architecture_android.data.api

import com.example.mvvm_architecture_android.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}