package com.igordudka.ui.widgets.request

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.data.model.UserDto
import com.igordudka.ui.R
import com.igordudka.ui.components.MediumText
import com.igordudka.ui.components.SemiboldText
import com.igordudka.ui.components.borderGray
import com.igordudka.ui.components.darkGray
import com.igordudka.ui.components.gray
import com.igordudka.ui.widgets.common.AppButton

@Composable
fun RequestWidget(
    userDto: UserDto,
    dismiss: () -> Unit,
    look: () -> Unit
) {

    Column(
        Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(0.3.dp, borderGray, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(shape = CircleShape, modifier = Modifier.size(70.dp), color = gray) {}
            Column (Modifier.padding(horizontal = 8.dp)){
                userDto.name?.let { SemiboldText(text = it, size = 15) }
                MediumText(text = "Совпадение:", size = 12, color = darkGray)
                Row {
                    AppButton(text = "", icon = R.drawable.dismiss, bgColor = gray, icColor = Color.Black) {
                        dismiss()
                    }
                    AppButton(text = "Рассмотреть", modifier = Modifier.weight(1f)) {
                        look()
                    }
                }
            }
        }
        userDto.bio?.let { MediumText(text = it, size = 16, maxLines = 3, padding = 12) }
    }
}