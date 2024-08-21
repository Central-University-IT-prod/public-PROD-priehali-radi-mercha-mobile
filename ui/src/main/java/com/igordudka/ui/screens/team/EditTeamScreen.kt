package com.igordudka.ui.screens.team

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.TeamDto
import com.igordudka.data.model.UserDto
import com.igordudka.ui.R
import com.igordudka.ui.components.DefaultButton
import com.igordudka.ui.components.blue
import com.igordudka.ui.components.gray
import com.igordudka.ui.widgets.auth.DefaultTextField
import com.igordudka.ui.widgets.common.AppButton
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget
import com.igordudka.ui.widgets.team.MembersBlock
import com.igordudka.ui.widgets.team.MembersBlockEdit
import com.igordudka.ui.widgets.team.TeamCard
import com.igordudka.ui.widgets.team.TeamCardEdit

@Composable
fun EditTeamScreen(
    teamDto: TeamDto?,
    goBack: () -> Unit,
    members: List<UserDto>?,
    removeMember: (String) -> Unit,
    changeName: (String) -> Unit,
    removeTeam: () -> Unit
) {

    BackHandler {
        goBack()
    }

    if (teamDto != null){
        var name by remember {
            mutableStateOf(teamDto.name)
        }


        Column(Modifier.fillMaxSize()) {
            TopBarWidget(name = "Изменить")
            TeamCardEdit(name = name) {
                name = it
            }
            LazyColumn {
                item {
                    if (members != null) {
                        MembersBlockEdit(owner = teamDto.owner, members = members) {
                            removeMember(it)
                        }
                    }
                    Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                        AppButton(text = "Сохранить", modifier = Modifier.weight(1f)) {
                            changeName(name)
                        }
                    }
                    Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                        AppButton(text = "Удалить команду", modifier = Modifier.weight(1f), bgColor = blue) {
                            removeTeam()
                        }
                    }
                }
            }
        }
    }else{
        LoaderWidget()
    }
}