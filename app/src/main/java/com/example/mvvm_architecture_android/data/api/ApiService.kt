package com.example.mvvm_architecture_android.data.api

import com.example.mvvm_architecture_android.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>
}