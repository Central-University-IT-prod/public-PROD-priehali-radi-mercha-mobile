package com.igordudka.feed.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.feed.viewmodel.FeedViewModel
import com.igordudka.ui.screens.team.TeamDetailScreen

@Composable
fun TeamDetail(
    token: String,
    teamId: String,
    feedViewModel: FeedViewModel = hiltViewModel(),
    goBack: () -> Unit
) {


    val teamUI by feedViewModel.teamDetail.collectAsState()
    val members by feedViewModel.members.collectAsState()

    LaunchedEffect(true) {
        feedViewModel.getTeam(teamId, token)
    }

    teamUI?.let {
        members?.let { it1 ->
            TeamDetailScreen(
                teamDto = it,
                members = it1,
                ownerDto = teamUI!!.owner,
                sendRequest = { feedViewModel.sendTicketForOwner(token, teamId)
                goBack()}) {
                goBack()
            }
        }
    }
}