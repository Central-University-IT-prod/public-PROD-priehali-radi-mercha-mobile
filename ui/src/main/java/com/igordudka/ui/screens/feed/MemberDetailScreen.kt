package com.igordudka.ui.screens.feed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.UserDto
import com.igordudka.ui.R
import com.igordudka.ui.components.blue
import com.igordudka.ui.components.green
import com.igordudka.ui.screens.profile.ProfileSection
import com.igordudka.ui.widgets.common.AppButton
import com.igordudka.ui.widgets.common.TopBarWidget

@Composable
fun MemberDetailScreen(
    userDto: UserDto,
    goBack: () -> Unit,
    accept: () -> Unit
) {
    BackHandler {
        goBack()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBarWidget(name = "Профиль")
        Column(Modifier.padding(12.dp)) {
            ProfileSection(userUIDto = userDto)
            Row (
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()){
                AppButton(text = "Пригласить", modifier = Modifier.weight(1f),
                    icColor = Color.White, bgColor = blue
                ) {
                    goBack()
                    accept()
                }
            }
        }
    }
}