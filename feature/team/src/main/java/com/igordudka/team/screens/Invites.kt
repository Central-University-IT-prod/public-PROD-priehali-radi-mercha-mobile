package com.igordudka.team.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.team.viewmodel.TeamViewModel
import com.igordudka.ui.screens.invites.InvitesScreen

@Composable
fun Invites(
    teamViewModel: TeamViewModel = hiltViewModel(),
    token: String,
    accept: (String) -> Unit
) {
    
    val teams by teamViewModel.invites.collectAsState()

    LaunchedEffect (true){
        teamViewModel.getInvites(token)
    }

    InvitesScreen(teams = teams) {
        
    }
}