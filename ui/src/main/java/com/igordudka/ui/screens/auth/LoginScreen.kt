package com.igordudka.ui.screens.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.igordudka.ui.components.BoldText
import com.igordudka.ui.components.blue
import com.igordudka.ui.widgets.auth.DefaultTextField
import com.igordudka.ui.widgets.common.AppButton

@Composable
fun LoginScreen(
    login: (String, String) -> Unit,
    goBack: () -> Unit,
    isError: Boolean
) {

    BackHandler {
        goBack()
    }

    var login by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        BoldText(text = "Вход", size = 25)
        Spacer(modifier = Modifier.height(60.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultTextField(value = login, onChange = { login = it }, placeholder = "Логин")
            DefaultTextField(
                value = password,
                onChange = { password = it },
                placeholder = "Пароль"
            )
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Войти", modifier = Modifier.weight(1f), bgColor =
                    if (login.length >= 6 && password.length >= 6) blue else blue.copy(alpha = 0.5f)
                ) {
                    if (login.length >= 6 && password.length >= 6) {
                        login(login, password)
                    }
                }
            }
        }
    }
}