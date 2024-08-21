package com.igordudka.ui.screens.auth

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.igordudka.data.model.OwnerDto
import com.igordudka.data.model.UserDto
import com.igordudka.data.model.Vacancy
import com.igordudka.ui.components.BoldText
import com.igordudka.ui.components.blue
import com.igordudka.ui.components.green
import com.igordudka.ui.widgets.auth.DefaultTextField
import com.igordudka.ui.widgets.auth.SpecChooser
import com.igordudka.ui.widgets.common.AppButton

private val choose = "choose"
private val asOwner = "asOwner"
private val asMember = "asMember"

@Composable
fun RegistrationScreen(
    regAsOwner: (OwnerDto, String, List<Vacancy>) -> Unit,
    regAsMember: (UserDto) -> Unit,
    goBack: () -> Unit
) {

    BackHandler {
        goBack()
    }

    var regPart by remember {
        mutableStateOf(choose)
    }

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        BoldText(text = "Регистрация", size = 25)
        Spacer(modifier = Modifier.height(60.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (regPart) {
                choose -> RegChooser(asMember = { regPart = asMember }) {
                    regPart = asOwner
                }

                asOwner -> OwnerRegistration (goBack = { regPart = choose }, onDone = { dto, teamName, vacancies ->
                    regAsOwner(dto, teamName, vacancies)
                })

                asMember -> MemberRegistration(goBack = { regPart = choose }, onDone =  { dto ->
                    regAsMember(dto)
                })
            }
        }
    }
}

@Composable
fun OwnerRegistration(
    onDone: (OwnerDto, String, List<Vacancy>) -> Unit,
    goBack: () -> Unit
) {

    var step by remember {
        mutableIntStateOf(0)
    }
    var name by remember {
        mutableStateOf("")
    }
    var login by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var teamName by remember {
        mutableStateOf("")
    }
    var vacancies = remember {
        mutableStateListOf<Vacancy>(
            Vacancy(
                "", ""
            )
        )
    }

    BackHandler {
        if (step == 0){
            goBack()
        }else{
            step--
        }
    }

    when (step) {
        0 -> {
            DefaultTextField(value = name, onChange = { name = it }, placeholder = "Имя")
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Далее",
                    modifier = Modifier.weight(1f),
                    bgColor = if (name.length >= 4) green else green.copy(alpha = 0.5f)
                ) {
                    if (name.length >= 4) {
                        step++
                    }
                }
            }
        }

        1 -> {
            DefaultTextField(
                value = teamName, onChange = { teamName = it }, placeholder = "Название команды"
            )
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Далее",
                    modifier = Modifier.weight(1f),
                    bgColor = if (teamName.length >= 4) green else green.copy(alpha = 0.5f)
                ) {
                    if (teamName.length >= 4) {
                        step++
                    }
                }
            }
        }

        2 -> {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                items(vacancies) {
                    SpecChooser(chosenItem = it.spec) {i->
                        vacancies[vacancies.indexOf(it)] = Vacancy(i, it.hards)
                    }
                    DefaultTextField(value = it.hards, onChange = { i ->
                        vacancies[vacancies.indexOf(it)] = Vacancy(it.spec, i)
                    }, placeholder = "Хард-скиллы")
                }
                item {
                    IconButton(onClick = { vacancies.add(Vacancy("", "")) }) {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                    }
                }
                item {
                    Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                        AppButton(
                            text = "Далее",
                            modifier = Modifier.weight(1f),
                            bgColor = if (vacancies.size >= 1 && vacancies[0].hards != "" && vacancies[0].spec != "") green else green.copy(
                                alpha = 0.5f
                            )
                        ) {
                            if (vacancies.size >= 1 && vacancies[0].hards != "" && vacancies[0].spec != "") {
                                step++
                            }
                        }
                    }
                }
            }
        }

        3 -> {
            DefaultTextField(
                value = login,
                onChange = { login = it },
                placeholder = "Логин",
                description = "Придумайте логин"
            )
            DefaultTextField(
                value = password,
                onChange = { password = it },
                placeholder = "Пароль",
                description = "Придумайте пароль (от 8 до 16 символов)"
            )
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Готово",
                    modifier = Modifier.weight(1f),
                    bgColor = if (login.length >= 6 && password.length >= 6) blue else blue.copy(
                        alpha = 0.5f
                    )
                ) {
                    if (login.length >= 6 && password.length >= 6) {
                        onDone(OwnerDto(name, login, password), teamName, vacancies)
                    }
                }
            }
        }
    }
}

@Composable
fun MemberRegistration(
    onDone: (UserDto) -> Unit,
    goBack: () -> Unit
) {

    var step by remember {
        mutableIntStateOf(0)
    }
    var name by remember {
        mutableStateOf("")
    }
    var spec by remember {
        mutableStateOf("")
    }
    var bio by remember {
        mutableStateOf("")
    }
    var softs by remember {
        mutableStateOf("")
    }
    var hards by remember {
        mutableStateOf("")
    }
    var telegram by remember {
        mutableStateOf("")
    }
    var login by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var chosenSpec by remember {
        mutableStateOf("")
    }
    BackHandler {
        if (step == 0){
            goBack()
        }else{
            step--
        }
    }

    when (step) {
        0 -> {
            DefaultTextField(value = name, onChange = { name = it }, placeholder = "Имя")
            SpecChooser(chosenItem = chosenSpec) {
                chosenSpec = it
            }
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Далее",
                    modifier = Modifier.weight(1f),
                    bgColor = if (name.length >= 4 && chosenSpec != "") green else green.copy(alpha = 0.5f)
                ) {
                    if (name.length >= 4 && chosenSpec != "") {
                        step++
                    }
                }
            }
        }

        1 -> {
            DefaultTextField(
                value = bio,
                onChange = { bio = it },
                placeholder = "Расскажите о себе",
                description = "Ваше био"
            )
            DefaultTextField(
                value = softs,
                onChange = { softs = it },
                placeholder = "Софт-скиллы",
                description = "Напишите ваши софт-скиллы"
            )
            DefaultTextField(
                value = hards,
                onChange = { hards = it },
                placeholder = "Хард-скиллы",
                description = "Напишите ваши хаод-скиллы"
            )
            DefaultTextField(
                value = telegram,
                onChange = { telegram = it },
                placeholder = "Ваш телеграм",
                description = "Укажите ниг в телеграме"
            )
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Далее",
                    modifier = Modifier.weight(1f),
                    bgColor = if (bio.isNotEmpty() && softs.isNotEmpty() && hards.isNotEmpty() && telegram.isNotEmpty()) green else green.copy(
                        alpha = 0.5f
                    )
                ) {
                    if (bio.isNotEmpty() && softs.isNotEmpty() && hards.isNotEmpty() && telegram.isNotEmpty()) {
                        step++
                    }
                }
            }
        }

        2 -> {
            DefaultTextField(
                value = login,
                onChange = { login = it },
                placeholder = "Логин",
                description = "Придумайте логин"
            )
            DefaultTextField(
                value = password,
                onChange = { password = it },
                placeholder = "Пароль",
                description = "Придумайте пароль (от 8 до 16 символов)"
            )
            Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                AppButton(
                    text = "Готово",
                    modifier = Modifier.weight(1f),
                    bgColor = if (login.length >= 6 && password.length >= 6) blue else blue.copy(
                        alpha = 0.5f
                    )
                ) {
                    if (login.length >= 6 && password.length >= 6) {
                        onDone(UserDto(
                            login = login,
                            password = password,
                            role = "member",
                            bio = bio,
                            name = name,
                            softSkills = softs.split(","),
                            hardSkills = hards.split(","),
                            specialization = chosenSpec,
                            telegram = telegram,
                        ))
                    }
                }
            }
        }
    }
}

@Composable
fun RegChooser(
    asMember: () -> Unit, asOwner: () -> Unit
) {
    Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
        AppButton(
            text = "Я ищу команду", modifier = Modifier.weight(1f), bgColor = blue
        ) {
            asMember()
        }
    }
    Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
        AppButton(
            text = "Я создаю команду", modifier = Modifier.weight(1f), bgColor = blue
        ) {
            asOwner()
        }
    }
}