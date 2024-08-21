package com.igordudka.ui.screens.team

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
import com.igordudka.ui.utils.OwnerTeamDto
import com.igordudka.ui.widgets.common.AppButton
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget
import com.igordudka.ui.widgets.team.MembersBlock
import com.igordudka.ui.widgets.team.TeamCard

@Composable
fun TeamScreen(
    teamDto: TeamDto?,
    members: List<UserDto>?,
    isOwner: Boolean,
    editTeam: () -> Unit,
    goToRequests: () -> Unit,
    leave: () -> Unit
) {

    if (teamDto != null){
        Column(Modifier.fillMaxSize()) {
            TopBarWidget(name = "Команда")
            TeamCard(name = teamDto.name, teamDto.color)
            if (isOwner){
                Row (
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()){
                    AppButton(text = "Редактировать", icon = R.drawable.edit, modifier = Modifier.weight(1f),
                        icColor = Color.White, bgColor = blue) {
                        editTeam()
                    }
                }
                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()) {
                    AppButton(text = "Заявки", icon = R.drawable.invites, modifier = Modifier.weight(1f),
                        txtColor = Color.Black, bgColor = gray, icColor = Color.Black) {
                        goToRequests()
                    }
                }
            }else{
                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()) {
                    AppButton(text = "Выйти из команды", icon = R.drawable.invites, modifier = Modifier.weight(1f),
                        txtColor = Color.Black, bgColor = gray, icColor = Color.Black) {
                        leave()
                    }
                }
            }
            if (members != null) {
                MembersBlock(
                    owner = teamDto.owner, members = members)
            }
        }
    }else{
        LoaderWidget()
    }
}