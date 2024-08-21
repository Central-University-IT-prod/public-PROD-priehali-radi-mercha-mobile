package com.igordudka.ui.screens.team

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.TeamDto
import com.igordudka.data.model.UserDto
import com.igordudka.ui.R
import com.igordudka.ui.components.blue
import com.igordudka.ui.components.gray
import com.igordudka.ui.widgets.common.AppButton
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget
import com.igordudka.ui.widgets.team.MembersBlock
import com.igordudka.ui.widgets.team.TeamCard

@Composable
fun TeamDetailScreen(
    teamDto: TeamDto?,
    members: List<UserDto>,
    ownerDto: UserDto,
    sendRequest: () -> Unit,
    goBack: () -> Unit
) {

    BackHandler {
        goBack()
    }

    if (teamDto != null){
        Column(Modifier.fillMaxSize()) {
            TopBarWidget(name = "Подробнее")
            TeamCard(name = teamDto.name, color = teamDto.color)
            Row (
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()){
                AppButton(text = "Отправить заявку", modifier = Modifier.weight(1f), bgColor = blue
                ) {
                    sendRequest()
                }
            }
            MembersBlock(
                owner = ownerDto
                , members = members)
        }
    }else{
        LoaderWidget()
    }
}