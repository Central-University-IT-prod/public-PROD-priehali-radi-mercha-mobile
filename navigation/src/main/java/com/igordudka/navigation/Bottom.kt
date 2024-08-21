package com.igordudka.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.igordudka.data.model.TeamDto
import com.igordudka.data.model.UserDto
import com.igordudka.data.utils.MEMBER
import com.igordudka.data.utils.OWNER
import com.igordudka.feed.screens.Feed
import com.igordudka.profile.screen.Profile
import com.igordudka.team.screens.Team
import com.igordudka.ui.screens.team.TeamScreen
import com.igordudka.ui.widgets.common.BottomBarWidget

private val search = "search"
private val team = "team"
private val profile = "profile"

@Composable
fun Bottom(
    token: String,
    role: String,
    showTeamDetail: (String) -> Unit,
    showMemberDetail: (String) -> Unit,
    goToRequests: (String) -> Unit,
    editProfile: () -> Unit,
    editTeam: () -> Unit,
    logout: () -> Unit
) {

    var screen by rememberSaveable {
        mutableStateOf(team)
    }


    Scaffold(
        bottomBar = {
            BottomBarWidget(
                screen = screen,
                onClick = { screen = it },
                screens = listOf(search, team, profile)
            )
        }
    ) { paddingValues ->
        Column(Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            when (screen) {
                search -> Feed(role = role, token = token) {
                    when(role){
                        MEMBER -> showTeamDetail(it)
                        OWNER -> showMemberDetail(it)
                    }
                }
                team -> Team(
                    editTeam = { editTeam()},
                    goToRequests = {goToRequests(it) },
                    role = role,
                    token = token,
                )
                profile -> Profile(goToEdit = editProfile, token = token) {
                    logout()
                }
            }
        }
    }
}