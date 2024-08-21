package com.igordudka.team.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.team.viewmodel.TeamViewModel
import com.igordudka.ui.screens.team.EditTeamScreen

@Composable
fun EditTeam(
    teamViewModel: TeamViewModel = hiltViewModel(),
    token: String,
    goBack: () -> Unit
) {

    val teamUI by teamViewModel.teamDetail.collectAsState()
    val members by teamViewModel.members.collectAsState()

    BackHandler {
        goBack()
    }

    LaunchedEffect(true) {
        teamViewModel.getTeam(token)
    }

    EditTeamScreen(
        teamDto = teamUI,
        goBack = { goBack() },
        members = members,
        removeMember = { teamViewModel.kickMember(token, it) },
        changeName = {teamViewModel.updateTeam(token, it)}
    ) {

    }
}