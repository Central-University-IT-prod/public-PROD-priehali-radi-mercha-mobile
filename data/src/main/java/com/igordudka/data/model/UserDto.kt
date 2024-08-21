package com.igordudka.data.model

data class UserDto(
    val login: String? = null,
    val password: String? = null,
    val role: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val bio: String? = null,
    val softSkills: List<String>? = null,
    val hardSkills: List<String>? = null,
    val specialization: String? = null,
    val telegram: String? = null,
)
