package com.igordudka.ui.utils

import com.igordudka.data.model.UserDto

data class TeamDetailUIDto(
    val name: String = "Name",
    val photoUrl: String? = "https://media.istockphoto.com/id/1388645967/photo/pensive-thoughtful-contemplating-caucasian-young-man-thinking-about-future-planning-new.jpg?s=612x612&w=0&k=20&c=Keax_Or9RivnYV_9VoOLjknWQP8iaxYXc4jS9rwBmcc=",
    val owner: UserDto,
    val members: List<MemberTeamDto> = listOf(MemberTeamDto())
)

data class OwnerTeamDto(
    val name: String = "Name",
    val telegram: String = "telegram",
)

data class MemberTeamDto(
    val name: String = "Name",
    val spec: String = "Spec",
    val softSkills: List<String>? = null,
    val hardSkills: List<String>? = null,
    val telegram: String = "Telegram",
)