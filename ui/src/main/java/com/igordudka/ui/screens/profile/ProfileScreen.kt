package com.igordudka.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.igordudka.data.model.UserDto
import com.igordudka.ui.R
import com.igordudka.ui.components.DefaultButton
import com.igordudka.ui.components.MediumText
import com.igordudka.ui.components.SemiboldText
import com.igordudka.ui.components.blue
import com.igordudka.ui.components.darkGray
import com.igordudka.ui.components.gray
import com.igordudka.ui.components.green
import com.igordudka.ui.utils.UserUIDto
import com.igordudka.ui.widgets.common.AppButton
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget

@Composable
fun ProfileScreen(
    userUIDto: UserDto?,
    editProfile: () -> Unit,
    logout: () -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBarWidget(name = "Профиль")
        if (userUIDto != null){
            Column(Modifier.padding(12.dp)) {
                ProfileSection(userUIDto = userUIDto)
                Row (
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()){
                    AppButton(text = "Редактировать", icon = R.drawable.edit, modifier = Modifier.weight(1f),
                        icColor = Color.White, bgColor = blue
                    ) {
                        editProfile()
                    }
                }
                Row (
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()){
                    AppButton(text = "Выйти", modifier = Modifier.weight(1f), bgColor = green
                    ) {
                        logout()
                    }
                }
            }
        }else{
            LoaderWidget()
        }
    }
}

@Composable
fun ProfileSection(
    userUIDto: UserDto
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Surface(shape = CircleShape, modifier = Modifier.size(100.dp), color = gray) {}
            Column(Modifier.padding(12.dp)) {
                userUIDto.name?.let { SemiboldText(text = it, size = 18) }
                userUIDto.specialization?.let { MediumText(text = it, size = 12, color = darkGray) }
            }
        }
        userUIDto.bio?.let { MediumText(text = it, size = 16) }
        userUIDto.hardSkills?.let {
            MediumText(text = it.joinToString(), size = 16)
        }
        userUIDto.telegram?.let { MediumText(text = it, size = 16) }
    }
}