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
fun OwnerCard(
    name: String,
    connect: () -> Unit
) {

    Column(
        Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(gray)
            .padding(16.dp)
    ) {
        Text(text = "Капитан")
        Text(text = name)
        DefaultButton(text = "Связаться", onClick = connect)
    }
}