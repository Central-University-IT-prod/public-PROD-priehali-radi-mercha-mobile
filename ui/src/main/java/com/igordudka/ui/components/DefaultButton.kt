package com.igordudka.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color = Color.White,
    backColor: Color = Color.Black
) {

    Button(onClick = onClick,
        modifier = modifier
            .padding(12.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = backColor,
            contentColor = textColor
        )) {
        Text(text = text, style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(4.dp))
    }
}