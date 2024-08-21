package com.igordudka.auth.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igordudka.auth.viewmodel.AuthViewModel
import com.igordudka.ui.screens.auth.AuthScreen
import com.igordudka.ui.screens.auth.LoginScreen
import com.igordudka.ui.screens.auth.RegistrationScreen

private const val REGISTER = "register"
private const val AUTH = "auth"
private const val LOGIN = "login"
private const val REDIRECT = "redirect"

@Composable
fun Auth(
    authViewModel: AuthViewModel = hiltViewModel(),
    goNext: (String, String) -> Unit
) {

    val navController = rememberNavController()
    val isLogin by authViewModel.isLogin.collectAsState()
    val token by authViewModel.token.collectAsState()
    val role by authViewModel.role.collectAsState()

    isLogin?.let {

        NavHost(navController = navController, startDestination = if (isLogin == true) REDIRECT else AUTH){
            composable(AUTH){
                AuthScreen(login = { navController.navigate(LOGIN) }) {
                    navController.navigate(REGISTER)
                }
            }
            composable(LOGIN){
                LoginScreen(login = {login, password->
                                    authViewModel.loginUser(login, password)
                }, isError = false, goBack = {navController.popBackStack()})
            }
            composable(REGISTER){
                RegistrationScreen(regAsOwner ={ownerDto, teamName, vacancies ->
                    authViewModel.registerOwner(ownerDto, teamName, vacancies)
                }, goBack = {navController.popBackStack()}, regAsMember =  {
                    authViewModel.registerMember(it)
                })
            }
            composable(REDIRECT){
                LaunchedEffect(key1 = true) {
                    token?.let { it1 -> role?.let { it2 -> goNext(it1, it2) } }
                }
            }
        }
    }
}