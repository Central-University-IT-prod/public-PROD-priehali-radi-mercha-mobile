package com.igordudka.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igordudka.auth.screen.Auth
import com.igordudka.data.model.TeamDto
import com.igordudka.data.model.UserDto
import com.igordudka.feed.screens.MemberDetail
import com.igordudka.feed.screens.TeamDetail
import com.igordudka.profile.screen.EditProfile
import com.igordudka.team.screens.EditTeam
import com.igordudka.team.screens.Invites
import com.igordudka.team.screens.RequestDetail
import com.igordudka.team.screens.Requests
import com.igordudka.ui.screens.profile.EditProfileScreen
import com.igordudka.ui.screens.requests.RequestDetailScreen
import com.igordudka.ui.screens.requests.RequestsScreen
import com.igordudka.ui.screens.team.EditTeamScreen
import com.igordudka.ui.screens.team.TeamDetailScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun App() {

    val navController = rememberNavController()
    var token by remember {
        mutableStateOf("")
    }
    var role by remember {
        mutableStateOf("")
    }
    var teamId by remember {
        mutableStateOf("")
    }
    var userId by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = AUTH) {
        composable(AUTH) {
            Auth { tkn, rol ->
                token = tkn
                role = rol
                navController.navigate(BOTTOM)
            }
        }
        composable(BOTTOM) {
            Bottom(
                showTeamDetail = {
                    teamId = it
                    Log.d("ID", teamId)
                    navController.navigate(TEAM_DETAIL) },
                goToRequests = {
                    teamId = it
                    navController.navigate(REQUESTS) }, editTeam = {
                    navController.navigate(
                        EDIT_TEAM
                    )
                }, editProfile = {
                    navController.navigate(EDIT_PROFILE)
                }, token = token, logout = {
                    scope.launch (Dispatchers.IO) { delay(1000)
                        navController.navigate(AUTH)
                    }

                }, role = role, showMemberDetail = {
                    userId = it
                    navController.navigate(MEMBER_DETAIL)
                })
        }
        composable(MEMBER_DETAIL){
            MemberDetail(token = token, userId = userId) {
                navController.popBackStack()
            }
        }
        composable(INVITES){
            Invites(token = token) {

            }
        }
        composable(EDIT_TEAM){
            EditTeam(token = token) {
                navController.popBackStack()
            }
        }
        composable(TEAM_DETAIL) {
            TeamDetail(token = token, teamId = teamId) {
                navController.popBackStack()
            }
        }
        composable(REQUESTS) {
            Requests(token = token, goBack = { navController.popBackStack() }) {
                navController.navigate(REQUEST_DETAIL)
                userId = it
            }
        }
        composable(REQUEST_DETAIL) {
            RequestDetail(token = token, userId = userId, teamId = teamId) {
                navController.popBackStack()
            }
        }
        composable(EDIT_PROFILE) {
            EditProfile(token = token) {
                navController.popBackStack()
            }
        }
    }
}