package com.igordudka.ui.screens.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.TeamDto
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget
import com.igordudka.ui.widgets.feed.FeedTeamCard

@Composable
fun FeedScreenForMember(
    teams: List<TeamDto>?,
    matches: List<String>?,
    showMore: (String) -> Unit
) {

    if (teams != null && matches != null){
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)) {
            TopBarWidget(name = "Поиск")
            LazyColumn(Modifier.padding(12.dp)) {
                items(teams){
                    FeedTeamCard(teamUIDto = it, match = matches[teams.indexOf(it)], showMore =  { showMore(it.teamId) })
                }
            }
        }
    }else{
        LoaderWidget()
    }
}