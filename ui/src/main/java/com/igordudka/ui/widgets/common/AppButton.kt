package com.igordudka.ui.widgets.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.igordudka.ui.components.BoldText
import com.igordudka.ui.components.green

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    txtColor: Color = Color.White,
    bgColor: Color = green,
    icColor: Color = Color.White,
    icon: Int? = null,
    onClick: () -> Unit
) {

    Button(onClick = onClick,
        modifier = modifier.padding(2.dp), shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor
        )
    ) {
        icon?.let {
            Icon(imageVector = ImageVector.vectorResource(id = icon), contentDescription = null,
                Modifier.size(24.dp), tint = icColor)
        }
        BoldText(text = text, size = 15, color = txtColor)
    }
}