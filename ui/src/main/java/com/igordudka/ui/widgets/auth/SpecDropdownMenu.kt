package com.igordudka.ui.widgets.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.utils.BACKEND
import com.igordudka.data.utils.FRONTEND
import com.igordudka.data.utils.MOBILE
import com.igordudka.ui.components.MediumText

@Composable
fun SpecChooser(
    chosenItem: String,
    choose: (String) -> Unit
) {

    val specs = listOf(MOBILE, BACKEND, FRONTEND)

    Column(Modifier.padding(vertical = 12.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        MediumText(text = "Спецификация", size = 16)
        LazyRow {
            items(specs){
                MenuItem(text = it, isChosen = it == chosenItem) {
                    choose(it)
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    text: String,
    isChosen: Boolean,
    onClick: () -> Unit
) {

    OutlinedButton(onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isChosen) Color.Black else Color.White
        ), modifier = Modifier.padding(6.dp)) {
        MediumText(text = text, size = 15, color = if (isChosen) Color.White else Color.Black)
    }
}