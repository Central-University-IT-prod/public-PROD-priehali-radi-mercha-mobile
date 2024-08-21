package com.igordudka.ui.widgets.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.UserDto
import com.igordudka.ui.components.DefaultButton
import com.igordudka.ui.components.gray
import com.igordudka.ui.utils.UserUIDto

@Composable
fun OwnerMemberCard(
    userUIDto: UserDto,
    more: () -> Unit
) {

    Column(
        Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(gray)
            .padding(12.dp)) {
        Row (verticalAlignment = Alignment.CenterVertically){
            userUIDto.name?.let { Text(text = it) }
            Column {
                CircularProgressIndicator()
                CircularProgressIndicator()
            }
        }
        DefaultButton(text = "Подробно", onClick = more)
    }
}