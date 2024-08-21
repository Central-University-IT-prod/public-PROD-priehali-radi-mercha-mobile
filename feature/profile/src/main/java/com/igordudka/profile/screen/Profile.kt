package com.igordudka.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.profile.viewmodel.ProfileViewModel
import com.igordudka.ui.screens.profile.ProfileScreen

@Composable
fun Profile(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    goToEdit: () -> Unit,
    token: String,
    logout: () -> Unit
) {

    val userUIState by profileViewModel.userUIState.collectAsState()

    LaunchedEffect(true) {
        profileViewModel.getUserInfo(token = token)
    }

    ProfileScreen(userUIDto = userUIState, editProfile = { goToEdit() }) {
        profileViewModel.logout()
        logout()
    }
}