package com.igordudka.ui.screens.requests

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.igordudka.data.model.UserDto
import com.igordudka.ui.widgets.common.LoaderWidget
import com.igordudka.ui.widgets.common.TopBarWidget
import com.igordudka.ui.widgets.request.RequestWidget

@Composable
fun RequestsScreen(
    requests: List<UserDto>?,
    goBack: () -> Unit,
    goToDetail: (String) -> Unit
) {

    BackHandler {
        goBack()
    }

    if (requests != null){
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            TopBarWidget(name = "Заявки")
            LazyColumn {
                items(requests){
                    RequestWidget(userDto = it, dismiss = { /*TODO*/ }) {
                        it.login?.let { it1 -> goToDetail(it1) }
                    }
                }
            }
        }
    }else{
        LoaderWidget()
    }
}