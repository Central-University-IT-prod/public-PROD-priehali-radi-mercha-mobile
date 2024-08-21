package com.igordudka.ui.widgets.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.igordudka.ui.components.DefaultButton
import com.igordudka.ui.components.gray

@Composable
fun MemberCard(
    name: String,
    spec: String,
    softs: List<String>?,
    hards: List<String>?,
    connect: () -> Unit,
    isForOwner: Boolean = false,
    kick: () -> Unit
) {
    Column(
        Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(gray)
            .padding(16.dp)
    ) {
        Text(text = "Участник")
        Text(text = name)
        Text(text = "Специализация: $spec")
        softs?.let {
            Text(text = "Софт-скиллы: ${it.joinToString()}")
        }
        hards?.let {
            Text(text = "Хард-скиллы: ${it.joinToString()}")
        }
        DefaultButton(text = "Связаться", onClick = connect)
        if (isForOwner){
            DefaultButton(text = "Выгнать", onClick = kick)
        }
    }
}