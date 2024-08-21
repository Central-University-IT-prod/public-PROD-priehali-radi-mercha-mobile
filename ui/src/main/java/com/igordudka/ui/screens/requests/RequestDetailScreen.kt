package com.igordudka.ui.screens.requests

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
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget

@Composable
fun RequestDetailScreen(
    userDto: UserDto?,
    goBack: () -> Unit,
    accept: () -> Unit,
    dissmiss: () -> Unit
) {

    BackHandler {
        goBack()
    }

    if (userDto != null){
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)) {
            TopBarWidget(name = "Заявка")
            Column(Modifier.padding(12.dp)) {
                ProfileSection(userUIDto = userDto)
                Row (
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()){
                    AppButton(text = "Принять", modifier = Modifier.weight(1f), bgColor = blue
                    ) {
                        accept()
                    }
                }
                Row (
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()){
                    AppButton(text = "Отклонить", modifier = Modifier.weight(1f), bgColor = green
                    ) {
                        dissmiss()
                    }
                }
            }
        }
    }else{
        LoaderWidget()
    }
}