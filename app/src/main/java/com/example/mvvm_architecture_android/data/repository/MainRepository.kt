package com.example.mvvm_architecture_android.data.repository

import com.example.mvvm_architecture_android.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
        suspend fun getUsers() = apiHelper.getUsers()
}