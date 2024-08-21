package com.igordudka.ui.screens.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.UserDto
import com.igordudka.ui.components.blue
import com.igordudka.ui.components.gray
import com.igordudka.ui.widgets.auth.DefaultTextField
import com.igordudka.ui.widgets.auth.SpecChooser
import com.igordudka.ui.widgets.common.AppButton
import com.igordudka.ui.widgets.common.TopBarWidget

@Composable
fun EditProfileScreen(
    userDto: UserDto,
    edit: (UserDto) -> Unit,
    goBack: () -> Unit
) {

    var name by remember {
        mutableStateOf(userDto.name)
    }
    var bio by remember {
        mutableStateOf(userDto.bio)
    }
    var hardSkills by remember {
        mutableStateOf(userDto.hardSkills)
    }
    var spec by remember {
        mutableStateOf(userDto.specialization)
    }
    var telegram by remember {
        mutableStateOf(userDto.telegram)
    }

    BackHandler {
        goBack()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarWidget(name = "Изменить профиль")
        LazyColumn(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Surface(shape = CircleShape, modifier = Modifier
                    .padding(12.dp)
                    .size(120.dp), color = gray) {}
                name?.let { s ->
                    DefaultTextField(
                        value = s,
                        onChange = { name = it },
                        placeholder = "Имя"
                    )
                }
                bio?.let { s ->
                    DefaultTextField(
                        value = s,
                        onChange = { bio = it },
                        placeholder = "Био"
                    )
                }
                hardSkills?.let { s ->
                    DefaultTextField(value = s.joinToString(),
                        onChange = { hardSkills = it.split(",") }, placeholder = "Хард-скиллы"
                    )
                }
                spec?.let { s ->
                    SpecChooser(chosenItem = s) {
                        spec = it
                    }
                }
                telegram?.let { s ->
                    DefaultTextField(
                        value = s,
                        onChange = { telegram = it },
                        placeholder = "Телеграм"
                    )
                }
            }
            item {
                Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                    AppButton(
                        text = "Сохранить",
                        modifier = Modifier.weight(1f),
                        bgColor = blue
                    ) {
                        edit(UserDto(
                            login = userDto.login,
                            password = userDto.password,
                            role = userDto.role,
                            name = name,
                            photoUrl = userDto.photoUrl,
                            bio = bio,
                            softSkills = userDto.softSkills,
                            hardSkills = hardSkills,
                            specialization = spec,
                            telegram = telegram
                        ))
                    }
                }
            }
        }
    }
}