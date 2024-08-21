package com.igordudka.data.model

import androidx.compose.ui.graphics.Color

val cardColors = listOf(
    Color(0xFF217BAD),
    Color(0xFFBD0AF4),
    Color(0xFFF89C0B),
    Color(0xFF673ECA),
    Color(0xFFD42C94)
)

data class TeamDto(
    val teamId: String,
    val owner: UserDto,
    val name: String,
    val createdAt: String,
    val maxParticipants: Int,
    val hardSkills: String,
    val color: Color = cardColors.random(),
)
