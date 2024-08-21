package com.igordudka.feed.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.igordudka.feed.viewmodel.FeedViewModel
import com.igordudka.ui.screens.feed.MemberDetailScreen

@Composable
fun MemberDetail(
    token: String,
    userId: String,
    feedViewModel: FeedViewModel = hiltViewModel(),
    goBack: () -> Unit
) {

    BackHandler {
        goBack()
    }

    LaunchedEffect(true) {
        feedViewModel.getMemberDetail(token, userId)
    }

    val memberDetail by feedViewModel.memberDetail.collectAsState()

    memberDetail?.let {
        MemberDetailScreen(userDto = it, goBack = goBack) {
            feedViewModel.sendTicketForMember(token, userId)
        }
    }
}