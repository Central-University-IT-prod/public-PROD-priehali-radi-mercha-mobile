package com.igordudka.feed.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.feed.viewmodel.FeedViewModel
import com.igordudka.ui.screens.feed.FeedScreenForMember
import com.igordudka.ui.screens.feed.FeedScreenForOwner

@Composable
fun Feed(
    feedViewModel: FeedViewModel = hiltViewModel(),
    role: String,
    token: String,
    goToDetail: (String) -> Unit,
) {

    LaunchedEffect(true) {
        when (role) {
            "owner" -> feedViewModel.searchForOwner(token)
            "member" -> feedViewModel.searchForMember(token)
        }
    }

    val teamsList by feedViewModel.teamsList.collectAsState()
    val membersList by feedViewModel.membersList.collectAsState()
    val matches by feedViewModel.matches.collectAsState()

    when (role) {
        "owner" -> {
            FeedScreenForOwner(members = membersList, invite = {goToDetail(it)})
        }
        "member" -> {
            FeedScreenForMember(teams = teamsList, matches = matches, showMore = {
                goToDetail(it)
            })
        }
    }
}