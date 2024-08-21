package com.igordudka.profile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordudka.data.model.UserDto
import com.igordudka.data.network.model.ProfileResponse
import com.igordudka.data.repository.AuthPreferencesRepository
import com.igordudka.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authPreferencesRepository: AuthPreferencesRepository
) : ViewModel(){

    private val _userUIState = MutableStateFlow<UserDto?>(null)
    val userUIState = _userUIState.asStateFlow()

    fun getUserInfo(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _userUIState.value = authRepository.getProfileInfo(token).toDto()
                Log.d("PROD", authRepository.getProfileInfo(token).toString())
            }catch (e: IOException){
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            authPreferencesRepository.saveLoginPreference(false)
        }
    }

    fun updateUser(token: String, userDto: UserDto){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val body = hashMapOf(
                    "username" to userDto.name!! as Any,
                    "description" to userDto.bio!! as Any,
                    "hardskill" to userDto.hardSkills as Any,
                    "softskill" to userDto.softSkills.toString() as Any,
                    "telegram" to userDto.telegram!! as Any,
                    "specialization" to userDto.specialization!! as Any
                )
                _userUIState.value = authRepository.updateProfile(token, body).toDto()
            }catch(e: Exception){

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