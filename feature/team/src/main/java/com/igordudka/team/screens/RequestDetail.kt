package com.igordudka.team.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.team.viewmodel.TeamViewModel
import com.igordudka.ui.screens.requests.RequestDetailScreen

@Composable
fun RequestDetail(
    teamViewModel: TeamViewModel = hiltViewModel(),
    token: String,
    userId: String,
    teamId: String,
    goBack: () -> Unit
) {

    val profile by teamViewModel.profileDetail.collectAsState()

    LaunchedEffect(true) {
        teamViewModel.getProfile(token, userId)
    }

    RequestDetailScreen(userDto = profile, goBack = goBack, accept = {
        teamViewModel.addMember(token, userId, teamId)
        goBack()
    }) {
        teamViewModel.removeTicket(token, userId, teamId)
        goBack()
    }
}