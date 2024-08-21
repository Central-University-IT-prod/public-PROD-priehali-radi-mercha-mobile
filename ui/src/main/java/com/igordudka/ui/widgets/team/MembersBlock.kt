package com.igordudka.ui.widgets.team

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.TeamDto
import com.igordudka.data.model.UserDto
import com.igordudka.ui.R
import com.igordudka.ui.components.RegularText
import com.igordudka.ui.components.SemiboldText
import com.igordudka.ui.components.darkGray
import com.igordudka.ui.components.gray
import com.igordudka.ui.utils.OwnerTeamDto
import com.igordudka.ui.widgets.common.AppButton

@Composable
fun MembersBlock(
    owner: UserDto,
    members: List<UserDto>
) {
    Column(Modifier.padding(12.dp)) {
        SemiboldText(text = "Участники", size = 16)
        owner.name?.let {
            owner.photoUrl?.let { it1 ->
                MemberTile(
                    name = it,
                    role = "Владелец",
                    img = it1
                )
            }
        }
        LazyColumn {
            items(members) {
                it.name?.let { it1 ->
                    it.specialization?.let { it2 ->
                        it.photoUrl?.let { it3 ->
                            MemberTile(
                                name = it1,
                                role = it2,
                                img = it3
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MembersBlockEdit(
    owner: UserDto,
    members: List<UserDto>,
    delete: (String) -> Unit
) {
    Column(Modifier.padding(12.dp)) {
        SemiboldText(text = "Участники", size = 16)
        owner.name?.let {
            owner.photoUrl?.let { it1 ->
                MemberTile(
                    name = it,
                    role = "Владелец",
                    img = it1
                )
            }
        }
        Column {
            for (it in members) {
                it.name?.let { it1 ->
                    it.specialization?.let { it2 ->
                        it.photoUrl?.let { it3 ->
                            MemberTileEdit(
                                name = it1,
                                role = it2,
                                img = it3,
                                delete = {
                                    Log.d("PROD", it.login.toString())
                                    it.login?.let { it4 -> delete(it4) } }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MemberTile(
    name: String,
    role: String,
    img: String
) {

    Row(
        Modifier
            .padding(6.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Surface(shape = CircleShape, modifier = Modifier.size(70.dp), color = gray) {

        }
        Column(Modifier.padding(12.dp)) {
            RegularText(text = name, size = 16)
            RegularText(text = role, size = 14, color = darkGray)
        }
    }
}

@Composable
fun MemberTileEdit(
    name: String,
    role: String,
    img: String,
    delete: () -> Unit
) {

    Row(
        Modifier
            .padding(6.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Surface(shape = CircleShape, modifier = Modifier.size(70.dp), color = gray) {
        }
        Column(Modifier.padding(12.dp)) {
            RegularText(text = name, size = 16)
            RegularText(text = role, size = 14, color = darkGray)
        }
        AppButton(text = "", icon = R.drawable.dismiss, bgColor = gray, icColor = Color.Black) {
            delete()
        }
    }
}