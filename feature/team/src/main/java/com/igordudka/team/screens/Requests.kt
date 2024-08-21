package com.igordudka.team.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.team.viewmodel.TeamViewModel
import com.igordudka.ui.screens.requests.RequestsScreen

@Composable
fun Requests(
    teamViewModel: TeamViewModel = hiltViewModel(),
    token: String,
    goBack: () -> Unit,
    goToDetail: (String) -> Unit
) {

    val requests by teamViewModel.requests.collectAsState()

    LaunchedEffect(true) {
        teamViewModel.getRequests(token)
    }

    RequestsScreen(requests = requests, goBack = goBack) {
        goToDetail(it)
    }
}