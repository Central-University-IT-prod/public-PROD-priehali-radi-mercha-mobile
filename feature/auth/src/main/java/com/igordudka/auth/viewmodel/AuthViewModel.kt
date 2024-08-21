package com.igordudka.auth.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordudka.data.model.OwnerDto
import com.igordudka.data.model.UserDto
import com.igordudka.data.repository.AuthPreferencesRepository
import com.igordudka.data.repository.AuthRepository
import com.igordudka.data.repository.TeamRepository
import com.igordudka.ui.states.NetworkUIState
import com.igordudka.data.model.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authPreferencesRepository: AuthPreferencesRepository,
    private val teamRepository: TeamRepository
) : ViewModel(){

    private val _loginUIState = MutableStateFlow<NetworkUIState>(NetworkUIState.Loading)
    val loginUIState = _loginUIState.asStateFlow()

    val isLogin = authPreferencesRepository.isLogin.map { it }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null
    )
    val token = authPreferencesRepository.token.map { it }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null
    )
    val role = authPreferencesRepository.role.map { it }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null
    )

    fun logout(){
        viewModelScope.launch {
            authPreferencesRepository.saveRolePreference("")
            authPreferencesRepository.saveLoginPreference(false)
            authPreferencesRepository.saveTokenPreference("")
        }
    }


    fun loginUser(login: String, password: String){
        _loginUIState.value = NetworkUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = hashMapOf(
                    "password" to password,
                    "login" to login
                )
                val response = authRepository.loginUser(body).response
                val role = authRepository.getProfileInfo(response).role
                authPreferencesRepository.saveRolePreference(role)
                authPreferencesRepository.saveLoginPreference(true)
                authPreferencesRepository.saveTokenPreference(response)
                _loginUIState.value = NetworkUIState.Success
            }catch (e: Exception){
                _loginUIState.value = NetworkUIState.Failed
            }
        }
    }

    fun registerOwner(ownerDto: OwnerDto, teamName: String, vacancies: List<Vacancy>){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val regBody = hashMapOf(
                    "password" to ownerDto.password,
                    "login" to ownerDto.login,
                    "role" to "owner"
                )
                val token = authRepository.registerUser(regBody).response
                val createTeamBody = hashMapOf(
                    "name" to teamName,
                    "description" to "Description(test)"
                )
                val teamId = teamRepository.createTeam(token, createTeamBody).response
                for (i in vacancies.indices){
                    val vacBody = hashMapOf(
                        "team_id" to teamId,
                        "hardskill" to vacancies[i].hards,
                        "specialization" to vacancies[i].spec
                    )
                    teamRepository.addVacancy(token, vacBody)
                }
                val memBody = hashMapOf(
                    "team_id" to teamId,
                    "login_member" to ownerDto.login
                )
                teamRepository.addMember(token, memBody)
                authPreferencesRepository.saveRolePreference("owner")
                authPreferencesRepository.saveLoginPreference(true)
                authPreferencesRepository.saveTokenPreference(token)
                updateOwner(ownerDto, token)
            }catch (e: IOException){
            }
        }
    }

    fun registerMember(userDto: UserDto){
        _loginUIState.value = NetworkUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val regBody = hashMapOf(
                    "password" to userDto.password.toString(),
                    "login" to userDto.login.toString(),
                    "role" to userDto.role.toString()
                )
                val token = authRepository.registerUser(regBody).response
                userDto.role?.let { authPreferencesRepository.saveRolePreference(it) }
                authPreferencesRepository.saveLoginPreference(true)
                authPreferencesRepository.saveTokenPreference(token)
                updateUser(userDto, token)
                _loginUIState.value = NetworkUIState.Success
            }catch (e: Exception){
                _loginUIState.value = NetworkUIState.Failed
            }
        }
    }
    private fun updateOwner(ownerDto: OwnerDto, token: String){
        viewModelScope.launch(Dispatchers.IO){
            val profileBody = hashMapOf(
                "username" to ownerDto.name.toString() as Any
            )
            authRepository.updateProfile(token, profileBody)
        }
    }
    private fun updateUser(userDto: UserDto, token: String){
        viewModelScope.launch(Dispatchers.IO) {
            val profileBody = hashMapOf(
                "username" to userDto.name.toString() as Any,
                "role" to userDto.role.toString() as Any,
                "picture" to userDto.photoUrl.toString() as Any,
                "description" to userDto.bio.toString() as Any,
                "hardskill" to userDto.hardSkills as Any,
                "softskill" to userDto.softSkills.toString() as Any,
                "telegram" to userDto.telegram.toString() as Any,
                "type" to userDto.specialization.toString() as Any,
                "specialization" to userDto.specialization.toString() as Any
            )
            authRepository.updateProfile(token, profileBody)
        }
    }
}