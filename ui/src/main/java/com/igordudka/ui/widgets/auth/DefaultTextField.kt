package com.igordudka.ui.widgets.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.ui.components.RegularText

@Composable
fun DefaultTextField(
    value: String,
    onChange: (String) -> Unit,
    placeholder: String,
    description: String? = null
) {

    OutlinedTextField(value = value, onValueChange = { onChange(it) },
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            RegularText(text = placeholder, size = 15, padding = 0)
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        supportingText = {
            description?.let { desc->
                RegularText(text = desc, size = 12, padding = 0)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            cursorColor = Color.Black
        ),
        trailingIcon = {
            IconButton(onClick = { onChange("") }) {
                Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
            }
        },
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
    )
}