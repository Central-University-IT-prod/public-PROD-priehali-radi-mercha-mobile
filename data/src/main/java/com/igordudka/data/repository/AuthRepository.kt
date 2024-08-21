package com.igordudka.data.repository

import com.igordudka.data.network.api.MainApi

class AuthRepository(
    private val mainApi: MainApi
) {

    suspend fun registerUser(body: HashMap<String, String>) = mainApi.registerUser(body)
    suspend fun loginUser(body: HashMap<String, String>) = mainApi.loginUser(body)
    suspend fun getProfileInfo(token: String) = mainApi.getProfileInfo(token)
    suspend fun updateProfile(token: String, body: HashMap<String, Any>) = mainApi.updateProfile(token, body)
}