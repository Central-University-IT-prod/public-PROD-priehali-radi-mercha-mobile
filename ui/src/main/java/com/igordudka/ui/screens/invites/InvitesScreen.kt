package com.igordudka.ui.screens.invites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.igordudka.data.model.TeamDto
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget
import com.igordudka.ui.widgets.feed.FeedTeamCard

@Composable
fun InvitesScreen(
    teams: List<TeamDto>?,
    show: (String) -> Unit
) {


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBarWidget(name = "Инвайты")
        if (teams != null){
            LazyColumn {
                items(teams){
                    FeedTeamCard(teamUIDto = it, showMore = { show(it.teamId) }, match = "50%")
                }
            }
        }else{
            LoaderWidget()
        }
    }
}