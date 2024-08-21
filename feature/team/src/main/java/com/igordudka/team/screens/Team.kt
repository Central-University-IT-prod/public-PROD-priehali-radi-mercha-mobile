package com.igordudka.team.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.data.utils.OWNER
import com.igordudka.team.viewmodel.TeamViewModel
import com.igordudka.ui.screens.team.TeamScreen

@Composable
fun Team(
    teamViewModel: TeamViewModel = hiltViewModel(),
    editTeam: () -> Unit,
    goToRequests: (String) -> Unit,
    role: String,
    token: String,
) {

    LaunchedEffect(true) {
        teamViewModel.getTeam(token)
        teamViewModel.getUserId(token)
    }
    val teamUI by teamViewModel.teamDetail.collectAsState()
    val members by teamViewModel.members.collectAsState()
    val profile by teamViewModel.profileDetail.collectAsState()

    TeamScreen(
        teamDto = teamUI,
        members = members,
        isOwner = role == OWNER,
        leave = {
            profile?.login?.let { it2 -> teamViewModel.leave(token, it2, teamUI!!.teamId) }
        },
        editTeam = { editTeam()}, goToRequests =  {
            goToRequests(teamUI!!.teamId)
        })
}