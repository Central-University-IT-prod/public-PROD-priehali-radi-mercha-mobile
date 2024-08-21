package com.igordudka.team.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordudka.data.model.TeamDto
import com.igordudka.data.model.UserDto
import com.igordudka.data.network.model.ProfileResponse
import com.igordudka.data.repository.AuthRepository
import com.igordudka.data.repository.TeamRepository
import com.igordudka.ui.utils.TeamDetailUIDto
import com.igordudka.ui.utils.UserUIDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    private val authRepository: AuthRepository
) : ViewModel(){

    private val _teamDetail = MutableStateFlow<TeamDto?>(null)
    val teamDetail = _teamDetail.asStateFlow()

    private val _members = MutableStateFlow<List<UserDto>?>(null)
    val members = _members.asStateFlow()

    private val _requests = MutableStateFlow<List<UserDto>?>(null)
    val requests = _requests.asStateFlow()

    private val _invites = MutableStateFlow<List<TeamDto>?>(null)
    val invites = _invites.asStateFlow()

    private val _profileDetail = MutableStateFlow<UserDto?>(null)
    val profileDetail = _profileDetail.asStateFlow()

    fun leave(token: String, login: String, teamId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = hashMapOf(
                    "team_id" to teamId,
                    "login_member" to login
                )
                teamRepository.kickMember(token, body)
                _teamDetail.value = null
            }catch (e: Exception){

            }
        }
    }

    fun getUserId(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.updateProfile(token, hashMapOf())
                _profileDetail.value = response.toDto()
            }catch (e: Exception){

            }
        }
    }

    fun addMember(token: String, login: String, teamId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val memBody = hashMapOf(
                    "team_id" to teamId,
                    "login_member" to login
                )
                val ticBody = hashMapOf(
                    "team_id" to teamId,
                    "login_member" to login
                )
                teamRepository.addMember(token, memBody)
                teamRepository.deleteTicket(token, ticBody)
            }catch (e: Exception){
                Log.d("ERROR", e.toString())
            }
        }
    }
    fun removeTicket(token: String, login: String, teamId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val memBody = hashMapOf(
                    "team_id" to teamId,
                    "login_member" to login,
                    "specialization" to "backend"
                )
                teamRepository.deleteTicket(token, memBody)
                getRequests(token)
            }catch (e: Exception){
                Log.d("ERROR", e.toString())
            }
        }
    }

    fun getProfile(token: String, id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("ID", id)
                val response = teamRepository.getProfile(token, id)
                _profileDetail.value = UserDto(name = response.username)
            }catch (e: Exception){

            }
        }
    }

    fun getRequests(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val id = teamRepository.getTeamId(token).response
                val response = teamRepository.getTicketsForOwner(token, id).response
                Log.d("PROD", response.toString())
                var tmp = mutableListOf<UserDto>()
                for (i in response){
                    val dto = teamRepository.getProfile(token, i.owner_id)
                    tmp.add(UserDto(
                        name = dto.username,
                        login = dto.login
                    ))
                }
                _requests.value = tmp
                Log.d("PROD", tmp.toString())
            }catch (e: Exception){
            }
        }
    }

    fun getInvites(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = teamRepository.getInvites(token)
                val tmp = mutableListOf<TeamDto>()
                for (i in response.response){
                    val get = teamRepository.getTeam(token, i.team_id).response
                    val owner = teamRepository.getProfile(token, get.owner)
                    tmp.add(
                        TeamDto(
                            get.team_id,
                            owner.toDto(),
                            get.name,
                            get.created_at,
                            get.max_participants,
                            get.hardskills,

                        )
                    )
                }
            }catch (e: Exception){
            }
        }
    }

    fun getTeam(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val teamId = teamRepository.getTeamId(token).response
                val getTeam = teamRepository.getTeam(token, teamId).response
                val ownerDto = teamRepository.getProfile(token, getTeam.owner).toDto()
                val membersResponse = teamRepository.getTeamMembers(token, getTeam.team_id).response
                val tmp = mutableListOf<UserDto>()
                for (i in membersResponse){
                    if (i != ownerDto.login){
                        tmp.add(teamRepository.getProfile(token, i).toDto())
                    }
                }
                _members.value = tmp
                _teamDetail.value = TeamDto(
                    teamId = getTeam.team_id,
                    owner = ownerDto,
                    createdAt = getTeam.created_at,
                    hardSkills = getTeam.hardskills,
                    maxParticipants = getTeam.max_participants,
                    name = getTeam.name
                )
            }catch (e: Exception){
                Log.d("PROD", e.toString())
            }
        }
    }

    fun updateTeam(token: String, name: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = hashMapOf(
                    "team_id" to _teamDetail.value?.teamId!!,
                    "name" to name
                )
                 teamRepository.updateTeam(token, body).response
                getTeam(token)
            }catch (e: Exception){

            }
        }
    }

    fun kickMember(token: String, login: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = hashMapOf(
                    "team_id" to _teamDetail.value?.teamId!!,
                    "login_member" to login
                )
                teamRepository.kickMember(token, body)
                getTeam(token)
            }catch (e: Exception){
                Log.d("PROD", e.toString())
            }
        }
    }

}


fun ProfileResponse.toDto() : UserDto{
    return UserDto(
        login = login,
        name = username,
        bio = description,
        hardSkills = hardskill,
        role = role,
        telegram = telegram,
        specialization = specialization.toString(),
        photoUrl = picture.toString()
    )
}