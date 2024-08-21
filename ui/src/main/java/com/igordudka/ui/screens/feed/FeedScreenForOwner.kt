package com.igordudka.ui.screens.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.igordudka.data.model.UserDto
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget
import com.igordudka.ui.widgets.feed.FeedMemberCard

@Composable
fun FeedScreenForOwner(
    members: List<UserDto>?,
    invite: (String) -> Unit
) {

    if (members != null){
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)) {
            TopBarWidget(name = "Поиск")
            LazyColumn {
                items(members){
                    FeedMemberCard(userDto = it, invite = { it.login?.let { it1 -> invite(it1) } }) {

                    }
                }
            }
        }
    }else{
        LoaderWidget()
    }
}