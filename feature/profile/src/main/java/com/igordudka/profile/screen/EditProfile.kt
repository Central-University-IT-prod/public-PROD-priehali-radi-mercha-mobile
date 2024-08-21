package com.igordudka.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.profile.viewmodel.ProfileViewModel
import com.igordudka.ui.screens.profile.EditProfileScreen

@Composable
fun EditProfile(
    token: String,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    goBack: () -> Unit
) {

    val profile by profileViewModel.userUIState.collectAsState()

    LaunchedEffect(true) {
        profileViewModel.getUserInfo(token)
    }

    profile?.let { userDto ->
        EditProfileScreen(userDto = userDto, edit = {
        profileViewModel.updateUser(token, it)
        goBack()
    }) {
        goBack()
    }
    }
}