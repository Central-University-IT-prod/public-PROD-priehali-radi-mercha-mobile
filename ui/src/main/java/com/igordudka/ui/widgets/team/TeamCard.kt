package com.igordudka.ui.widgets.team

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igordudka.ui.components.BoldText
import com.igordudka.ui.components.MediumText
import com.igordudka.ui.components.RegularText
import com.igordudka.ui.components.borderGray
import com.igordudka.ui.components.darkGray
import com.igordudka.ui.components.gray
import com.igordudka.ui.widgets.auth.DefaultTextField
import com.igordudka.ui.widgets.common.AppButton

@Composable
fun TeamCard(
    name: String,
    color: Color
) {


    Column(
        Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = 0.5f))
            .border(0.3.dp, borderGray, RoundedCornerShape(12.dp))
    ) {
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .height(0.3.dp), color = borderGray
        )
        Row (Modifier.fillMaxWidth().background(Color.White)){
            BoldText(text = name, size = 15, padding = 12)
        }
    }
}

@Composable
fun TeamCardEdit(
    name: String,
    onChange: (String) -> Unit
) {

    Column(
        Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(0.3.dp, borderGray, RoundedCornerShape(12.dp))
    ) {
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .height(0.3.dp), color = borderGray
        )
        DefaultTextField(value = name, onChange = { onChange(it) }, placeholder = "Имя")
    }
}