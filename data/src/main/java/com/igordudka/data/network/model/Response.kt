package com.igordudka.data.network.model

data class Response(
    val created_at: String,
    val hardskills: String,
    val max_participants: Int,
    val name: String,
    val owner: String,
    val team_id: String
)