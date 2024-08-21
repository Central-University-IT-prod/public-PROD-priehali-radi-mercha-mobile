package com.igordudka.ui.utils

data class TeamUIDto(
    val name: String = "Name",
    val photoUrl: String? = "https://media.istockphoto.com/id/1388645967/photo/pensive-thoughtful-contemplating-caucasian-young-man-thinking-about-future-planning-new.jpg?s=612x612&w=0&k=20&c=Keax_Or9RivnYV_9VoOLjknWQP8iaxYXc4jS9rwBmcc=",
    val membersCount: Int = 5,
    val remainingMembers: Int = 10,
    val already: List<String> = listOf("Backend"),
    val needed: List<String> = listOf("Mobile"),
)
