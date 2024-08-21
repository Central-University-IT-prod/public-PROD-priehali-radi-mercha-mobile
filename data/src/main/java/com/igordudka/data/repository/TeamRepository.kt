package com.igordudka.data.repository

import com.igordudka.data.model.Vacancy
import com.igordudka.data.network.api.MainApi

class TeamRepository(
    private val mainApi: MainApi
) {

    suspend fun createTeam(token: String, body: HashMap<String, String>) = mainApi.createTeam(token, body = body)
    suspend fun getTeam(token: String, teamId: String) = mainApi.getTeam(token, teamId)
    suspend fun addVacancy(token: String, body: HashMap<String, String>) = mainApi.addVacancy(token, body)
    suspend fun getTeamId(token: String) = mainApi.getTeamId(token)
    suspend fun addMember(token: String, body: HashMap<String, String>) = mainApi.addMember(token, body)
    suspend fun getTicketsForOwner(token: String, teamId: String) = mainApi.getTicketsForOwner(token, teamId)
    suspend fun getProfile(token: String, id: String) = mainApi.getProfile(token, id)
    suspend fun getTeamMembers(token: String, teamId: String) = mainApi.getMembers(token, teamId)
    suspend fun updateTeam(token: String, body: HashMap<String, String>) = mainApi.editTeam(token, body)
    suspend fun kickMember(token: String, body: HashMap<String, String>) = mainApi.kickMember(token, body)
    suspend fun deleteTicket(token: String, body: HashMap<String, String>) = mainApi.ticketDelete(token, body)
    suspend fun getInvites(token: String) = mainApi.getInvites(token)
}