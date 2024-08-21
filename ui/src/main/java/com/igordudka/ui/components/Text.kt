package com.igordudka.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegularText(
    text: String,
    size: Int,
    color: Color = Color.Black,
    padding: Int = 6
) {

    Text(
        text = text, fontSize = size.sp, fontFamily = interFamily, fontWeight = FontWeight.Normal,
        color = color, modifier = Modifier.padding(padding.dp)
    )
}

@Composable
fun MediumText(
    text: String,
    size: Int,
    color: Color = Color.Black,
    padding: Int = 6,
    maxLines: Int = 100
) {

    Text(
        text = text, fontSize = size.sp, fontFamily = interFamily, fontWeight = FontWeight.Medium,
        color = color, modifier = Modifier.padding(padding.dp), maxLines = maxLines
    )
}

@Composable
fun SemiboldText(
    text: String,
    size: Int,
    color: Color = Color.Black,
    padding: Int = 6
) {

    Text(
        text = text, fontSize = size.sp, fontFamily = interFamily, fontWeight = FontWeight.SemiBold,
        color = color, modifier = Modifier.padding(padding.dp)
    )
}

@Composable
fun BoldText(
    text: String,
    size: Int,
    color: Color = Color.Black,
    padding: Int = 6
) {

    Text(
        text = text, fontSize = size.sp, fontFamily = interFamily, fontWeight = FontWeight.Bold,
        color = color, modifier = Modifier.padding(padding.dp)
    )
}