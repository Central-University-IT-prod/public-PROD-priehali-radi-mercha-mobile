package com.igordudka.feed.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordudka.data.model.TeamDto
import com.igordudka.data.model.UserDto
import com.igordudka.data.network.model.ProfileResponse
import com.igordudka.data.repository.FeedRepository
import com.igordudka.data.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val _teamsList = MutableStateFlow<List<TeamDto>?>(null)
    val teamsList = _teamsList.asStateFlow()

    private val _memberDetail = MutableStateFlow<UserDto?>(null)
    val memberDetail = _memberDetail.asStateFlow()

    private val _matches = MutableStateFlow<MutableList<String>?>(mutableListOf())
    val matches = _matches.asStateFlow()

    private val _membersList = MutableStateFlow<List<UserDto>?>(null)
    val membersList = _membersList.asStateFlow()

    private val _teamDetail = MutableStateFlow<TeamDto?>(null)
    val teamDetail = _teamDetail.asStateFlow()

    private val _members = MutableStateFlow<List<UserDto>?>(null)
    val members = _members.asStateFlow()

    fun getMemberDetail(token: String, userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _memberDetail.value = teamRepository.getProfile(token, userId).toDto()
            }catch (e: Exception){

            }
        }
    }

    fun getTeam(teamId: String, token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val getTeam = teamRepository.getTeam(token, teamId).response
                val ownerDto = teamRepository.getProfile(token, getTeam.owner).toDto()
                val membersResponse = teamRepository.getTeamMembers(token, getTeam.team_id).response
                val tmp = mutableListOf<UserDto>()
                for (i in membersResponse){
                    tmp.add(teamRepository.getProfile(token, i).toDto())
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

    fun sendTicketForMember(token: String, userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val teamId = teamRepository.getTeamId(token).response
                val body = hashMapOf(
                    "team_id" to teamId,
                    "specialization" to "Backend",
                    "user_login" to userId
                )
                feedRepository.sendTicketToOwner(token, body)
                Log.d("PROD", body.toString())
            }catch (e: Exception){
                Log.d("HUY", e.toString())
            }
        }
    }

    fun sendTicketForOwner(token: String, teamId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = hashMapOf(
                    "team_id" to teamId,
                    "specialization" to "Backend"
                )
                feedRepository.sendTicketToOwner(token, body)
                Log.d("PROD", body.toString())
            }catch (e: Exception){
                Log.d("HUY", e.toString())
            }
        }
    }

    fun searchForOwner(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val search = feedRepository.search(token)
                val tmp = mutableListOf<UserDto>()
                for (i in search){
                    i["login"]?.let {
                        val response = teamRepository.getProfile(token, it)
                        tmp.add(
                            UserDto(
                                name = response.username,
                                login = response.login
                            )
                        )
                    }
                }
                _membersList.value = tmp
                Log.d("PROD", tmp.toString())
            }catch (e: Exception){
                Log.d("HUY", e.toString())
            }
        }
    }

    fun searchForMember(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var tmp = mutableListOf<TeamDto>()
                val lst = feedRepository.search(token)
                for (i in lst){
                    i["percent"]?.let { _matches.value?.add(it) }
                    i["login"]?.let {
                        val response = teamRepository.getTeam(token, it).response
                        val owner = teamRepository.getProfile(token, response.owner)
                        tmp.add(TeamDto(
                            name = response.name,
                            teamId = response.team_id,
                            owner = owner.toDto(),
                            createdAt = response.created_at,
                            maxParticipants = response.max_participants,
                            hardSkills = response.hardskills
                        ))
                    }
                }
                _teamsList.value = tmp
            }catch (e: Exception){
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