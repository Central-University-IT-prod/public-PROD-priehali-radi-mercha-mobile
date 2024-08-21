package com.igordudka.data.network.model

data class ProfileResponse(
    val description: String,
    val hardskill: List<String>,
    val login: String,
    val role: String,
    val softskill: List<String>,
    val telegram: String,
    val specialization: String?,
    val username: String,
    val picture: String?
)